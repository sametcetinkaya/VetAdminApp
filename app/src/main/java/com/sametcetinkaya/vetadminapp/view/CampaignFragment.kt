package com.sametcetinkaya.vetadminapp.view


import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sametcetinkaya.vetadminapp.R
import com.sametcetinkaya.vetadminapp.adapter.RecyclerAdapter
import com.sametcetinkaya.vetadminapp.viewModel.CampaignAddViewModel
import kotlinx.android.synthetic.main.add_campaign.*
import kotlinx.android.synthetic.main.fragment_campaign.*
import java.lang.Exception


class CampaignFragment : Fragment() {

    private lateinit var campaignAddViewModel: CampaignAddViewModel
    private val recyclerAdapter = RecyclerAdapter(arrayListOf())
    private var choosenImage : Uri? = null
    private var choosenBitmap : Bitmap? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campaign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        campaignAddViewModel = ViewModelProviders.of(this).get(CampaignAddViewModel::class.java)
        campaignAddViewModel.refreshData()
        setupRecycler()
        observerLiveData()
        addCampaign.setOnClickListener {
            button(it)
        }



    }

    private fun observerLiveData() {
        campaignAddViewModel.campaignAdd.observe(viewLifecycleOwner, Observer { campaignAdd ->
            campaignAdd?.let {
                campaignRecyclerView.visibility= View.VISIBLE
                recyclerAdapter.campaignListUpdate(campaignAdd)
            }
        })
    }

    private fun setupRecycler() {
        campaignRecyclerView.layoutManager = LinearLayoutManager(context)
        campaignRecyclerView.adapter = recyclerAdapter

    }
    private fun button(view: View)
    {
        val setDialogView = LayoutInflater.from(context).inflate(R.layout.add_campaign, null)
        // AlertDialog nesnemizi üretiyoruz
        val alert = AlertDialog.Builder(context).setView(setDialogView).show()
        alert.chooseImage.setOnClickListener {
                openGallery(it)
        }

        // Başlık
        //alert.setTitle("Çıkış")
        //Mesaj
        //alert.setMessage("Çıkış yapmak istediğinize emin misiniz?")
        //Herhangi bir boşluğa basınca kapanmaması için true olursa kapanır
        //Geri tuşununu da pasif hale getiriyoruz
        //alert.setCancelable(false);
        //AlertDialog'un iconunu belirliyoruz
        //alert.setIcon(R.drawable.report);

        //alert.setPositiveButton("Evet") { dialogInterface: DialogInterface, i: Int ->
            // Evet butonuna tıklayınca olacaklar
            //Toast.makeText(context,"Evet",Toast.LENGTH_LONG).show() }

        //alert.setNegativeButton("Hayır") {dialogInterface: DialogInterface, i: Int ->
            // Hayır butonuna tıklayınca olacaklar
            //Toast.makeText(context,"Hayır",Toast.LENGTH_LONG).show() }

    }
    private fun openGallery(view: View){

            activity?.let {
                if (ContextCompat.checkSelfPermission(it.applicationContext,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    //izin verilmedi, izin istememiz gerekiyor
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
                } else {
                    //izin zaten verilmiş, tekrar istemeden galeriye git
                    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent,2)
                }
            }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1){

            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //izni aldık
                val galleryIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent,2)

            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){

            choosenImage = data.data

            try {

                context?.let {
                    if(choosenImage != null) {
                        if( Build.VERSION.SDK_INT >= 28){
                            val source = ImageDecoder.createSource(it.contentResolver,choosenImage!!)
                            choosenBitmap = ImageDecoder.decodeBitmap(source)
                            campaignImageView.setImageBitmap(choosenBitmap) //Hangi değişkene gidecekse veri imageView kısmına o yazılacak
                        } else {
                            choosenBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver,choosenImage)
                            campaignImageView.setImageBitmap(choosenBitmap)
                        }
                    }
                }

            } catch (e: Exception){
                e.printStackTrace()
            }


        }



        super.onActivityResult(requestCode, resultCode, data)
    }



}