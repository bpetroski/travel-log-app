package com.travelbetadisaster.travel_log.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.travelbetadisaster.travel_log.R
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode


class MapsFragment : Fragment() {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mapsViewModel: MapsViewModel

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val lastKnownLocation = mapsViewModel.getLastKnownLocation()
        if (lastKnownLocation != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude), DEFAULT_ZOOM.toFloat()))
            mapsViewModel.saveLocation(LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude))
        } else {
            updateLocationUI()
            getDeviceLocation()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val place = Autocomplete.getPlaceFromIntent(data)
                Log.i("MapsFragment", "Place: ${place.name}, ${place.id}, ${place.latLng}")

                // Check if the place has a LatLng
                place.latLng?.let { latLng ->
                    // Move the camera to the place's LatLng
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM.toFloat()))
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // Handle the error.
                val status = Autocomplete.getStatusFromIntent(data!!)
                Log.i("MapsFragment", status.statusMessage ?: "")
            }
        }
    }


    private fun updateLocationUI() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
            mMap.uiSettings.isMyLocationButtonEnabled = true
        } else {
            mMap.isMyLocationEnabled = false
            mMap.uiSettings.isMyLocationButtonEnabled = false
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    private fun getDeviceLocation() {
        try {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful && task.result != null) {
                        // Set the map's camera position to the current location of the device.
                        val lastKnownLocation = task.result
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                            LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude), DEFAULT_ZOOM.toFloat()))
                    } else {
                        Log.d("MapsFragment", "Current location is null. Using defaults.")
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat()))
                        mMap.uiSettings.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("MapsFragment", "Exception: ${e.message}")
        }
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapsViewModel = ViewModelProvider(this).get(MapsViewModel::class.java)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        val searchButton = view.findViewById<Button>(R.id.search_places_button)
        searchButton.setOnClickListener {
            openAutocompleteActivity()
        } // This closing brace was missing

        // Initialize the Places API
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), "AIzaSyCCFtPA4pDJ_-kwrCSK6isuxVCtJ5mYtjA")
        }
        val placesClient: PlacesClient = Places.createClient(requireActivity())

        // Example usage of ViewModel to retrieve or save the last known location
        // Consider implementing these in response to user actions or lifecycle events
    }

    private fun openAutocompleteActivity() {
        // Define the fields to specify which types of place data to return.
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)

        // Start the autocomplete intent.
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(requireContext())
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }


    companion object {
        private const val AUTOCOMPLETE_REQUEST_CODE = 1
        private const val REQUEST_LOCATION_PERMISSION = 1
        private const val DEFAULT_ZOOM = 15
        private val defaultLocation = LatLng(42.2644, 83.6647)
    }
}
