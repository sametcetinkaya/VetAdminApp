package com.sametcetinkaya.vetadminapp.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.sametcetinkaya.vetadminapp.R
import com.sametcetinkaya.vetadminapp.viewModel.AddCampaignViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.campaign_card.*
import kotlinx.android.synthetic.main.fragment_add_campaign.*
import kotlinx.android.synthetic.main.fragment_add_campaign.view.*
import java.io.ByteArrayOutputStream
import java.lang.Exception


class AddCampaignFragment : Fragment() {

    var choosenImage: Uri? = null
    var choosenBitmap: Bitmap? = null
    private lateinit var campaignAddViewModel: AddCampaignViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_campaign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        campaignAddViewModel = ViewModelProviders.of(this).get(AddCampaignViewModel::class.java)
        observerLiveData()
        chooseImage.setOnClickListener {
            gorselSec(it)
        }
        addCampaignButton.setOnClickListener {
            addCampaign()
        }
    }

    private fun gorselSec(view: View) {

        activity?.let {
            if (ContextCompat.checkSelfPermission(
                    it.applicationContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //izin verilmedi, izin istememiz gerekiyor
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            } else {
                //izin zaten verilmiş, tekrar istemeden galeriye git
                val galeriIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent, 2)
            }
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {

            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //izni aldık
                val galeriIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent, 2)

            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {

            choosenImage = data.data

            try {

                context?.let {
                    if (chooseImage != null) {
                        if (Build.VERSION.SDK_INT >= 28) {
                            val source =
                                ImageDecoder.createSource(it.contentResolver, choosenImage!!)
                            choosenBitmap = ImageDecoder.decodeBitmap(source)
                             choosenBitmap = kucukBitmapOlustur(choosenBitmap!!,200)
                            Log.i("bitmapsonuç", "" + choosenBitmap)
                            campaignImageView.setImageBitmap(choosenBitmap)
                        } else {
                            choosenBitmap =
                                MediaStore.Images.Media.getBitmap(it.contentResolver, choosenImage)
                            campaignImageView.setImageBitmap(choosenBitmap)
                        }

                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }


        }



        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun kucukBitmapOlustur(kullanicininSectigiBitmap: Bitmap, maximumBoyut: Int): Bitmap {

        var width = kullanicininSectigiBitmap.width
        var height = kullanicininSectigiBitmap.height

        val bitmapOrani: Double = width.toDouble() / height.toDouble()

        if (bitmapOrani > 1) {
            // görselimiz yatay
            width = maximumBoyut
            val kisaltilmisHeight = width / bitmapOrani
            height = kisaltilmisHeight.toInt()
        } else {
            //görselimiz dikey
            height = maximumBoyut
            val kisaltilmisWidth = height * bitmapOrani
            width = kisaltilmisWidth.toInt()

        }

        return Bitmap.createScaledBitmap(kullanicininSectigiBitmap, width, height, true)
    }

    private fun imageToString(): String {

        val byteArrayOutputStream = ByteArrayOutputStream()
        choosenBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageBytes: ByteArray = byteArrayOutputStream.toByteArray()
        val imageString: String = Base64.encodeToString(imageBytes, Base64.DEFAULT)
        return imageString

    }

    private fun observerLiveData() {

        campaignAddViewModel.campaignAddData.observe(viewLifecycleOwner, Observer {
            it.let { AddCampaign ->
                AddCampaign
                Toast.makeText(context, it.text, Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun addCampaign() {
            if (choosenBitmap != null){
            if (!imageToString().equals("") && !titleEditText.text.toString()
                    .equals("") && !explanationEditText.text.toString().equals(""))
            {
                campaignAddViewModel.getCampaignAdd(
                    titleEditText.text.toString(),explanationEditText.text.toString(),imageToString()

                )
                Log.i("eklemebasarılı", "" + titleEditText.text)
                titleEditText.setText("")
                explanationEditText.setText("")
            }}
            else {
                Toast.makeText(
                    context,
                    "Tüm alanların doldurulması ve resmin seçilmesi zorunludur.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    private fun action() {

    }
}