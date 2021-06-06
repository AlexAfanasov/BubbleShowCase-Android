package com.elconfidencial.bubbleshowcase.testapp

import android.app.Dialog
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import com.elconfidencial.bubbleshowcase.BubbleShowCase
import com.elconfidencial.bubbleshowcase.BubbleShowCaseBuilder
import com.elconfidencial.bubbleshowcase.BubbleShowCaseListener
import com.elconfidencial.bubbleshowcase.BubbleShowCaseSequence
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*

class FragmentTest: BottomSheetDialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }
    private var rootViewWTF : View? = null

    private fun setUpListeners(){
        buttonSimpleShowCase.setOnClickListener { getSimpleShowCaseBuilder().show() }
        buttonColorShowCase.setOnClickListener { getCustomColorShowCaseBuilder().show() }
        buttonTextSizeShowCase.setOnClickListener { getTextSizeShowCaseBuilder().show() }
        buttonArrowLeftShowCase.setOnClickListener { getArrowLeftShowCaseBuilder().show() }
        buttonArrowRightShowCase.setOnClickListener { getArrowRightShowCaseBuilder().show() }
        buttonEventListener.setOnClickListener { getListenerShowCaseBuilder().show() }
        buttonSequence.setOnClickListener { getSequence().show() }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        bottomSheetDialog.setOnShowListener{
            rootViewWTF = (it as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.container)
        }
        return bottomSheetDialog
    }
//SHOW CASES GETTERS

    private fun getSimpleShowCaseBuilder(): BubbleShowCaseBuilder {
        return BubbleShowCaseBuilder(this.activity!!)
            .title("Welcome!!!")
            .viewRoot(rootViewWTF)
            .description("This is a simple BubbleShowCase with default values.")
            .targetView(buttonSimpleShowCase)
    }

    private fun getCustomColorShowCaseBuilder(): BubbleShowCaseBuilder {
        return BubbleShowCaseBuilder(this.activity!!)
            .title("Custom your bubble style!")
            .description("It is possible to change the text color, background ... and you can even add an image into your bubble.")
            .backgroundColor(ContextCompat.getColor(this.activity!!, R.color.colorBlueGray))
            .image(ContextCompat.getDrawable(this.activity!!, R.drawable.ic_color)!!)
            .closeActionImage(ContextCompat.getDrawable(this.activity!!, R.drawable.ic_close_black)!!)
            .textColor(ContextCompat.getColor(this.activity!!, R.color.colorBlack))
            .targetView(buttonColorShowCase)
    }

    private fun getTextSizeShowCaseBuilder(): BubbleShowCaseBuilder {
        return BubbleShowCaseBuilder(this.activity!!)
            .title("Change text sizes!")
            .description("You can also choose the best text size for you.")
            .backgroundColor(ContextCompat.getColor(this.activity!!, R.color.colorTeal))
            .image(ContextCompat.getDrawable(this.activity!!, R.drawable.ic_format_size)!!)
            .titleTextSize(18)
            .descriptionTextSize(16)
            .closeActionImage(null)
            .targetView(buttonTextSizeShowCase)
    }

    private fun getArrowLeftShowCaseBuilder(): BubbleShowCaseBuilder {
        return BubbleShowCaseBuilder(this.activity!!)
            .title("Force the position of the bubble!")
            .description("You only have to specify in which side you want the arrow, and the bubble will be located depending on it.")
            .arrowPosition(BubbleShowCase.ArrowPosition.LEFT)
            .backgroundColor(ContextCompat.getColor(this.activity!!, R.color.colorRed))
            .targetView(buttonArrowLeftShowCase)
    }

    private fun getArrowRightShowCaseBuilder(): BubbleShowCaseBuilder {
        return BubbleShowCaseBuilder(this.activity!!)
            .title("Arrow set on right side this time :)")
            .arrowPosition(BubbleShowCase.ArrowPosition.RIGHT)
            .backgroundColor(ContextCompat.getColor(this.activity!!, R.color.colorPink))
            .targetView(buttonArrowRightShowCase)
    }


    private fun getListenerShowCaseBuilder(): BubbleShowCaseBuilder {
        return BubbleShowCaseBuilder(this.activity!!)
            .title("Listen user actions!")
            .description("You can detect when the user interacts with the different view elements to act consequently.")
            .backgroundColor(ContextCompat.getColor(this.activity!!, R.color.colorOrange))
            .image(ContextCompat.getDrawable(this.activity!!, R.drawable.ic_sentiment_satisfied)!!)
            .listener(object : BubbleShowCaseListener {
                override fun onBubbleClick(bubbleShowCase: BubbleShowCase) {
//                    Toast.makeText(this@MainActivity, "OnBubbleClick", Toast.LENGTH_SHORT).show()
                }

                override fun onBackgroundDimClick(bubbleShowCase: BubbleShowCase) {
//                    Toast.makeText(this@MainActivity, "OnBackgroundDimClick", Toast.LENGTH_SHORT).show()
                }

                override fun onTargetClick(bubbleShowCase: BubbleShowCase) {
//                    Toast.makeText(this@MainActivity, "OnTargetClick", Toast.LENGTH_SHORT).show()
                }

                override fun onCloseActionImageClick(bubbleShowCase: BubbleShowCase) {
//                    Toast.makeText(this@MainActivity, "OnClose", Toast.LENGTH_SHORT).show()
                }
            })
            .targetView(buttonEventListener)
    }

    private fun getSequence(): BubbleShowCaseSequence {
        return BubbleShowCaseSequence().addShowCases(listOf(
            getSimpleShowCaseBuilder(),
            getCustomColorShowCaseBuilder(),
            getTextSizeShowCaseBuilder(),
            getArrowLeftShowCaseBuilder(),
            getArrowRightShowCaseBuilder(),
            getListenerShowCaseBuilder()
        ))
    }
}
