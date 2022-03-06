package com.example.mygym.screen.aboutgym

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mygym.*
import com.example.mygym.adapter.ViewPagerImageAdapter
import com.example.mygym.databinding.FragmentAboutGymBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_about_gym.*
import me.relex.circleindicator.CircleIndicator3

class AboutGymFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentAboutGymBinding
    private var imagesList = mutableListOf<Int>()
    val mapsFragment: MapsFragment = MapsFragment()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        showActionBar(requireActivity() as AppCompatActivity)
        binding = FragmentAboutGymBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postToList()

        imageViewPager.adapter = ViewPagerImageAdapter(imagesList)

        val indicator: CircleIndicator3 = view.findViewById(R.id.indicator)
        indicator.setViewPager(imageViewPager)

        binding.emailLinearLayout.setOnClickListener {
            val packageManager: PackageManager = requireActivity().packageManager
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$GYM_EMAIL") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, GYM_EMAIL)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        binding.teamLinearLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Инструкторы", Toast.LENGTH_SHORT).show()
        }

        binding.tableLinearLayout.setOnClickListener {
            Toast.makeText(requireContext(), "Расписание", Toast.LENGTH_SHORT).show()
        }

        binding.phoneButton.setOnClickListener {
            try {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:+79613089885")
                        startActivity(callIntent)
            } catch (activityException: ActivityNotFoundException) {
                Log.e("Calling a Phone Number", "Call failed")
            }
        }

        binding.shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, GYM_SHARE_TEXT)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        binding.goToInstButton.setOnClickListener {
            val launchBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(GYM_INST))
            startActivity(launchBrowser)
        }

        binding.goToVkButton.setOnClickListener {
            val launchBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(GYM_VK))
            startActivity(launchBrowser)
        }

        binding.goToWebsiteButton.setOnClickListener {
            val launchBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(GYM_WEBSITE))
            startActivity(launchBrowser)
        }

        parentFragmentManager.beginTransaction().   replace(R.id.frameLayout, mapsFragment).commit()

    }

    private fun addToList(image: Int) {
        imagesList.add(image)
    }


    //delete or add page of pager
    private fun postToList() {

        for (i in 1..5) {
            when (i) {
                1 -> addToList(R.drawable.man_powerlift)
                2 -> addToList(R.drawable.boxing)
                3 -> addToList(R.drawable.woman_fitness)
                4 -> addToList(R.drawable.man_powerlift)
                5 -> addToList(R.drawable.boxing)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        imagesList.clear()
    }

    override fun onMapReady(p0: GoogleMap) {
        p0.addMarker(MarkerOptions()
            .position(LatLng(47.229505163672684, 39.628286684591885))
            .title("WestGym"))
    }

}
