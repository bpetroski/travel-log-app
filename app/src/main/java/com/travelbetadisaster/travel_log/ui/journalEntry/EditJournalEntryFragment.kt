package com.travelbetadisaster.travel_log.ui.journalEntry

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.content.Context
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.travelbetadisaster.travel_log.MainActivity
import com.travelbetadisaster.travel_log.database.tables.TbdLocation
import com.travelbetadisaster.travel_log.database.tables.Visit
import com.travelbetadisaster.travel_log.databinding.FragmentEditJournalEntryBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class EditJournalEntryFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEditJournalEntryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: JournalEntryViewModel
        get() = (activity as MainActivity).journalEntryViewModel
    private var entryId: Int? = null

    private var fileTimestamp = 0
    private var fileName = "journal_image_${fileTimestamp}.jpg"
    var photoAttached = false
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val photo: Bitmap? = data?.extras?.get("data") as? Bitmap
            //this way entries with no photo will have a 0 preventing the other fragments from calling the database
            fileTimestamp = System.currentTimeMillis().toInt()
            fileName = "journal_image_${fileTimestamp}.jpg"
            photo?.let {
                saveBitmapToInternalStorage(it, fileName)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditJournalEntryBinding.inflate(inflater, container, false)

        // Assuming ID as a fragment
        arguments?.let {
            entryId = it.getInt("entryId")
        }

        setupListeners()



        return binding.root
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            saveEntry()
        }
        binding.btnAddPhoto.setOnClickListener {
            openCamera()
        }
        binding.btnSetLocation.setOnClickListener {

        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }
    private fun saveBitmapToInternalStorage(bitmap: Bitmap, fileName: String) {
        // Save the bitmap to internal storage

        val outputStream = requireContext().openFileOutput(fileName, Context.MODE_PRIVATE)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.close()
        photoAttached = true
    }

    private fun saveEntry() {

        val newEntryTitle = binding.journalTitle.text.toString()
        val newEntryImage = fileTimestamp
        val newEntryDescription = binding.journalDescription.text.toString()
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
        val newEntryDateTime = LocalDateTime.now().format(formatter)

        val locationID = (activity as MainActivity).getLocationID()
        val currentLocation = viewModel.getLocation(locationID)



        val newEntryTbdLocation = TbdLocation(
            id = locationID, // this doesn't really work for an id but we're running outta time and I can't figure out how to make it work the right way
            name = binding.journalEntryLocation.text.toString(),
            description = "",
            lattitude = (activity as MainActivity).getLatitude(),
            longitude = (activity as MainActivity).getLongitude()
        )

        val existingTbdLocation = TbdLocation(
            id = currentLocation.value?.id ?: 0,
            name = currentLocation.value?.name.toString(),
            description = "",
            lattitude = currentLocation.value?.lattitude.toString(),
            longitude = currentLocation.value?.longitude.toString()
        )


        Log.e("lat", newEntryTbdLocation.lattitude!!)

//        testing what happens when you getLocation for a non exisiting location ID
/*        Log.e("non existing location id", viewModel.getLocation(6969).value?.id.toString())
        Log.e("non real id is null?", (viewModel.getLocation(6969).value?.id == null).toString()) // always shows true
        Log.e("real id?", (viewModel.getLocation(37841857).value?.id == null).toString()) // always shows true
        Log.e("can i get the name?", viewModel.getLocation(37841857).value!!.name.toString()) // returns null
*/

        if(newEntryTitle == "" || newEntryDescription == "" || newEntryDateTime == ""){ // check input
            Toast.makeText(activity,"Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }else{
            val entry = Visit(newEntryTitle, locationID, newEntryImage, newEntryDescription, newEntryDateTime)
            if(currentLocation.value?.id == null){ // new entry with new location
                Log.e("zzz","Construct and pass new Location")
                viewModel.saveVisit(entry, newEntryTbdLocation)
                dismiss()
            }else{ // new entry with existing location
                Log.e("zzz", "Construct and pass existing Location")
                viewModel.saveVisit(entry, existingTbdLocation)
                dismiss()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REQUEST_CODE_CAMERA = 101
    }
}
