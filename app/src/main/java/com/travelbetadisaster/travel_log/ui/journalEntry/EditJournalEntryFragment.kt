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
        val newLocationID = (activity as MainActivity).getTime()
        val newEntryTitle = binding.journalTitle.text.toString()
//      pulls location title from EditText box and lat/long from device current location using callLocation from MainActivity.
        val newEntryTbdLocation = TbdLocation(
            id = newLocationID, // this doesn't really work for an id but we're running outta time and I can't figure out how to make it work the right way
            name = binding.journalEntryLocation.text.toString(),
            description = "",
            lattitude = (activity as MainActivity).getLatitude(),
            longitude = (activity as MainActivity).getLongitude()
        )
        Log.e("lat", newEntryTbdLocation.lattitude!!)
/*        val newEntryImage = 0
        if(photoAttached){val newEntryImage = fileTimestamp.toInt()}*/
        val newEntryImage = fileTimestamp
        val newEntryDescription = binding.journalDescription.text.toString()
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
        val newEntryDateTime = LocalDateTime.now().format(formatter)

        if(newEntryTitle == "" || newEntryDescription == "" || newEntryDateTime == ""){
            Toast.makeText(activity,"Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }else{
            val entry = Visit(newEntryTitle, newLocationID, newEntryImage, newEntryDescription, newEntryDateTime)
            viewModel.saveVisit(entry, newEntryTbdLocation)
            dismiss()
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
