package com.example.project.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.project.MainActivity
import com.example.project.MainActivity.Companion.bottomSheetDialog
import com.example.project.MainActivity.Companion.dialogFrameLayout
import com.example.project.MainActivity.Companion.firstPoint
import com.example.project.MainActivity.Companion.secondPoint
import com.example.project.MainActivity.Companion.textMapping
import com.example.project.R
import com.example.project.databinding.FragmentBottomSheetBinding


class BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentBottomSheetBinding
    var from:Int=1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        dialog!!.setOnShowListener { dialog ->
            bottomSheetDialog = dialog as BottomSheetDialog
            dialogFrameLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            BottomSheetBehavior.from(dialogFrameLayout).state =
                BottomSheetBehavior.STATE_COLLAPSED
        }
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        binding = FragmentBottomSheetBinding.bind(view)
        return binding.root
    }

    override fun getTheme(): Int = R.style.BottomSheetDialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.dismiss()

        return super.onCreateDialog(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        with(binding) {
            arguments?.let {
                from=it.getInt("from")
                when(it.getString("point")){
                    tvBin.tag -> tvBin.setTextColor(requireContext().resources.getColor(R.color.project))
                    tvOct.tag -> tvOct.setTextColor(requireContext().resources.getColor(R.color.project))
                    tvDec.tag -> tvDec.setTextColor(requireContext().resources.getColor(R.color.project))
                    else ->tvHex.setTextColor(requireContext().resources.getColor(R.color.project))
                }


            }

            cancel.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            tvBin.setOnClickListener {
                saveView(it)

            }
            tvDec.setOnClickListener {
                saveView(it)
            }
            tvOct.setOnClickListener {
                saveView(it)
            }
            tvHex.setOnClickListener {
                saveView(it)
            }
        }
    }

    private fun saveView(it: View) {
        if (from==1){
            firstPoint=it.tag.toString()
            textMapping["textView1"] = firstPoint
            MainActivity.binding.spinner1.text=firstPoint
        }
        else{
            secondPoint=it.tag.toString()
            textMapping["textView2"] = secondPoint
            MainActivity.binding.spinner2.text=secondPoint
        }
        bottomSheetDialog.dismiss()

    }


    /*
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)
    fun expendDialog(activity: FragmentActivity?, logTag: String, performOnError: () -> Unit){
        try {
            val bottomSheet = dialog!!.findViewById(R.id.fragmentContainer) as View
            val behavior = BottomSheetBehavior.from(bottomSheet)
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager!!.defaultDisplay!!.getMetrics(displayMetrics)
            behavior.peekHeight = displayMetrics.heightPixels
        } catch (e: NullPointerException) {
            d(logTag, e.message ?: "NPE in onResume")
            performOnError()
        }
    }*/
}