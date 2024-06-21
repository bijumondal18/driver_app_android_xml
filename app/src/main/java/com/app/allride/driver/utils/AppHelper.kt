package com.app.allride.driver.utils

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationManagerCompat
import com.app.allride.driver.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.BuildConfig
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Hashtable
import java.util.Locale
import java.util.regex.Pattern

class AppHelper {

    companion object {
        private const val TAG = "APP_HELPER"
        private const val PLAY_SERVICES_RESOLUTION_REQUEST = 9000
        private lateinit var progressDialogBuilder: AlertDialog.Builder
        private lateinit var progressDialog: AlertDialog
        private var mPreference: AppPreference? = null
        private var htLogs: Hashtable<String, ArrayList<String>> = Hashtable()
        private lateinit var toolBar: Toolbar
        private lateinit var actionBar: ActionBar

        fun toastShort(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        fun toastNetworkError(context: Context) {
            Toast.makeText(
                context,
                "Something went wrong! Please check your internet connection.",
                Toast.LENGTH_SHORT
            ).show()
        }

        fun toastLong(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun showDebugLog(tag: String, msg: String) {
            Log.d(tag, msg)
        }

        fun showInfoLog(tag: String, msg: String) {
            Log.i(tag, msg)
        }

        fun showErrorLog(tag: String, msg: String) {
            Log.e(tag, msg)
        }

        fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }

        fun formatDate(date: Date): String {
            val format = SimpleDateFormat("dd, MMM, yyyy", Locale.getDefault())
            return format.format(date)
        }

        ////////  make full screen transparent background with status bar and navigation bar start /////////
        /*fun statusBarBackground(activity: AppCompatActivity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = activity.window
                val background = activity.resources.getDrawable(R.drawable.background_gredient_1)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor(activity.resources.getColor(android.R.color.transparent))
                window.navigationBarColor(activity.resources.getColor(android.R.color.transparent))
                window.setBackgroundDrawable(background)
            }
        }*/
        ////////  make full screen trannsparent background with status bar and navigation bar end /////////


        fun getOSVersion(): String {
            return Build.VERSION.RELEASE
        }

        fun getDeviceSDKVersion(): Int {
            return Build.VERSION.SDK_INT
        }

        fun getDeviceName(): String? {
            return Build.MODEL
        }

        fun getCurrentDeviceTime(): String {
            val currentTime = Calendar.getInstance().time
            return currentTime.toString()
        }

        fun getAppVersion(): String {
            return BuildConfig.VERSION_NAME
        }

        @SuppressLint("HardwareIds")
        fun getDeviceId(context: Context): String? {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun isNotificationEnabled(context: Context): Boolean {
            return NotificationManagerCompat.from(context).areNotificationsEnabled()
        }

        fun getCabType(type: Int): String {
            var cabType: String? = null
            when (type) {
                1 -> cabType = "Compact (Economy)"
                2 -> cabType = "SUV (Economy)"
                3 -> cabType = "Sedan (Economy)"
                4 -> cabType = "Tempo Traveller (Economy)"
                5 -> cabType = "Assured Dzire (Economy)"
                6 -> cabType = "Assured Innova (Economy)"
                7 -> cabType = "Tempo Traveller (9 seater) (Economy)"
                8 -> cabType = "Tempo Traveller (12 seater) (Economy)"
                9 -> cabType = "Tempo Traveller (15 seater) (Economy)"
                10 -> cabType = "Tempo Traveller (19 seater) (Economy)"
                11 -> cabType = "Shared Sedan (Economy)"
                12 -> cabType = "Tempo Traveller (26 seater) (Economy)"
                13 -> cabType = "SUV (7+1) (Economy)"
            }
            return cabType!!
        }

        fun getTripType(type: Int): String {
            var tripType: String? = null
            when (type) {
                1 -> tripType = "One-Way"
                2 -> tripType = "Round Trip"
                3 -> tripType = "Round / Multi Trip"
                4 -> tripType = "Airport Transfer"
                5 -> tripType = "Tour Package"
                6 -> tripType = "Flexxi"
                7 -> tripType = "Shuttle"
                8 -> tripType = "Package"
                9 -> tripType = "Day Rental (4hr-40km)"
                10 -> tripType = "Day Rental (8hr-80km)"
                11 -> tripType = "Day Rental (12hr-120km)"
                12 -> tripType = "Others"
            }
            return tripType!!
        }

        fun getUserGender(type: Int): String {
            var gender: String? = null
            when (type) {
                //0 -> gender = "Others"
                1 -> gender = "Male"
                2 -> gender = "Female"

            }
            return gender!!
        }


        fun getErrorCode(type: Int): String {
            var errorCode: String? = null
            when (type) {
                101 -> errorCode = "ERROR_FAILED"
                102 -> errorCode = "ERROR_SERVER"
                103 -> errorCode = "ERROR_UNKNOWN"
                104 -> errorCode = "ERROR_VALIDATION"
                105 -> errorCode = "ERROR_INVALID_DATA"
                106 -> errorCode = "ERROR_NO_RECORDS_FOUND"
                107 -> errorCode = "ERROR_REQUEST_CANNOT_PROCEED"
                108 -> errorCode = "ERROR_UNAUTHORISED"

            }
            return errorCode!!
        }

        fun getStateCode(type: Int): String {
            var stateCode: String? = null
            when (type) {
                80 -> stateCode = "Andhra Pradesh"
                81 -> stateCode = "Arunachal Pradesh"
                82 -> stateCode = "Assam"
                83 -> stateCode = "Bihar"
                84 -> stateCode = "Chhattisgarh"
                85 -> stateCode = "Goa"
                86 -> stateCode = "Gujarat"
                87 -> stateCode = "Haryana"
                88 -> stateCode = "Himachal Pradesh"
                89 -> stateCode = "Jammu and Kashmir"
                90 -> stateCode = "Jharkhand"
                91 -> stateCode = "Karnataka"
                92 -> stateCode = "Kerala"
                93 -> stateCode = "Madhya Pradesh"
                94 -> stateCode = "Maharashtra"
                95 -> stateCode = "Manipur"
                96 -> stateCode = "Meghalaya"
                97 -> stateCode = "Mizoram"
                98 -> stateCode = "Nagaland"
                99 -> stateCode = "Odisha"
                100 -> stateCode = "Punjab"
                101 -> stateCode = "Rajasthan"
                102 -> stateCode = "Sikkim"
                103 -> stateCode = "Tamil Nadu"
                104 -> stateCode = "Tripura"
                105 -> stateCode = "Uttar Pradesh"
                106 -> stateCode = "Uttarakhand"
                107 -> stateCode = "West Bengal"
                108 -> stateCode = "Delhi"
                109 -> stateCode = "Dadra and Nagar Haveli"
                110 -> stateCode = "Andaman and Nicobar Islands"
                111 -> stateCode = "Daman and Diu"
                112 -> stateCode = "Puducherry"
                113 -> stateCode = "Lakshadweep"
                114 -> stateCode = "Chandigarh"
                115 -> stateCode = "Telangana"
            }
            return stateCode!!
        }


        fun getDeviceType(type: Int): String {
            var deviceType: String? = null
            when (type) {
                3 -> deviceType = "Platform_Android"
                4 -> deviceType = "Platform_Ios"
            }
            return deviceType!!
        }


        fun showLoading(context: Context) {
            progressDialogBuilder = AlertDialog.Builder(context)
            progressDialogBuilder.setCancelable(false) // if you want user to wait for some process to finish,
            progressDialogBuilder.setView(R.layout.layout_loading_dialog)
            progressDialog = progressDialogBuilder.create()
            progressDialog.show()
        }


        fun hideLoading() {
            try {

                if (progressDialog.isShowing)
                    progressDialog.dismiss()

            } catch (ex: java.lang.Exception) {
                Log.e(TAG, ex.toString())
            }
        }

        fun setBoldTypeface(context: Context, textView: TextView) {
            textView.typeface = Typeface.createFromAsset(context.assets, "fonts/Roboto-Bold.ttf")
        }

        fun setMediumTypeface(context: Context, textView: TextView) {
            textView.typeface = Typeface.createFromAsset(context.assets, "fonts/Roboto-Medium.ttf")
        }

        fun setMediumTypefaceButton(context: Context, btn: Button) {
            btn.typeface = Typeface.createFromAsset(context.assets, "fonts/Roboto-Medium.ttf")
        }

        fun setMediumTypefaceRadioButton(context: Context, btn: RadioButton) {
            btn.typeface = Typeface.createFromAsset(context.assets, "fonts/Roboto-Medium.ttf")
        }

        fun setRegularTypefaceButton(context: Context, btn: Button) {
            btn.typeface = Typeface.createFromAsset(context.assets, "fonts/Roboto-Regular.ttf")
        }

        fun setRegularTypeface(context: Context, textView: TextView) {
            textView.typeface = Typeface.createFromAsset(context.assets, "fonts/Roboto-Regular.ttf")
        }

        fun getColoredText(text: String, color: String): String {
            val input = "<font color=$color>$text</font>"
            return input
        }

//        fun showRefresh(refresh: SwipeRefreshLayout) {
//            if (refresh != null) {
//                refresh.isRefreshing = true
//                refresh.isEnabled = true
//            }
//        }
//
//        fun hideRefresh(refresh: SwipeRefreshLayout) {
//            if (refresh != null) {
//                refresh.isRefreshing = false
//                refresh.isEnabled = false
//            }
//        }

        fun showSNACK(view: View, msg: String) {
            val snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
            val layoutParams = LayoutParams(snackBar.view.layoutParams)
            layoutParams.gravity = Gravity.NO_GRAVITY
//            snackBar.setActionTextColor(Color.WHITE)
            val snackBarView = snackBar.view
            snackBarView.setBackgroundColor(Color.DKGRAY)
            snackBarView.setPadding(16, 0, 16, 40)
            snackBarView.layoutParams = layoutParams
            val textView: TextView =
                snackBarView.findViewById(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(Color.LTGRAY)
//            textView.textSize = 15f
//            snackBar.setAction("OK") { snackBar.dismiss() }
            snackBar.show()
        }

        fun setTextOnTextView(tv: TextView, str: String) {
            if (!TextUtils.isEmpty(str)) {
                tv.text = str
            } else {
                tv.text = "NA"
            }
        }

        fun setTextOnEditText(edt: EditText, str: String) {
            if (!TextUtils.isEmpty(str)) {
                edt.setText(str)
            } else {
                edt.setText("NA")
            }
        }

        fun isValidEmail(str: String): Boolean {
            return !TextUtils.isEmpty(str) && Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
            ).matcher(str).matches()
        }


        fun isValidPhoneNumber(str: String): Boolean {
            return !TextUtils.isEmpty(str)

        }

        fun isValidString(str: String): Boolean {
            return !TextUtils.isEmpty(str)

        }

        fun isValidPickupDropLocation(str: String, isProvideLater: Boolean): Boolean {
            return if (isProvideLater) {
                true
            } else {
                !TextUtils.isEmpty(str)
            }
        }

//        fun showAppCloseAlert(context: Context) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_alert)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//
//            dialog.findViewById<Button>(R.id.btn_positive).setOnClickListener {
//                dialog.dismiss()
//                (context as AppCompatActivity).finish()
//            }
//            dialog.findViewById<Button>(R.id.btn_negative).setOnClickListener {
//                dialog.dismiss()
//            }
//        }

//        fun showMessageDialogOnly(context: Context, msg: String) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_alert)
//            dialog.setCanceledOnTouchOutside(false)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.setCanceledOnTouchOutside(false)
//            dialog.show()
//            dialog.tv_title.text = msg
//            dialog.btn_negative.visibility = View.GONE
//            dialog.findViewById<Button>(R.id.btn_positive).setOnClickListener {
//                dialog.dismiss()
//                (context as AppCompatActivity).finish()
//            }
//
//            /*dialog.findViewById<Button>(R.id.btn_negative).setOnClickListener {
//                dialog.dismiss()
//            }*/
//        }

        /*
        fun showDuePaymentBottomSheet(context: Context, msg: String) {
            val view: View = (context as AppCompatActivity).layoutInflater.inflate(R.layout.dialog_due_payment_bottom_sheet, null)
            val dialog = BottomSheetDialog(context)
            dialog.setContentView(view)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()

            dialog.tv_due_amnt.text = msg
            dialog.btn_cancel_dialog.setOnClickListener {
                dialog.dismiss()
            }
            mPreference = GozoPreference(context)

            dialog.findViewById<Button>(R.id.btn_pay_due_amount)!!.setOnClickListener {
                val amount = dialog.edt_due_amount.text.trim().toString()

                if (amount == null || amount.isEmpty()) {
                    toastShort(context, "Please enter a valid amount")
                } else if (amount != null && mFare != null && mFare!!.dueAmount != null && amount > mFare!!.dueAmount.toString()) {
                    toastShort(context, "amount should be less than ${mFare!!.dueAmount}")

                } else {
                    if (isConnectedToInternet(context)) {
                        val req = RequestGenerateChecksum(amount.toInt(), mBilling!!, 6, 1, mData!!.id)
                        generateChecksum(req, context)
                        dialog.dismiss()

                    } else {
                        toastShort(context, "network error!")

                    }
                }
                //(context as AppCompatActivity).finish()
            }

        }

        private fun generateChecksum(req: RequestGenerateChecksum, context: Context) {

            showLog(TAG, "Request Generate Checksum : $req")
            showLoading(context)
            showLog(TAG, "Auth: ${mPreference.getAuth()!!}")

            val call: Call<ResponseGenerateChecksum> = GozoAPIService.create().generateChecksum(mPreference.getAuth()!!, 1, req)
            call.enqueue(object : Callback<ResponseGenerateChecksum> {
                override fun onResponse(call: Call<ResponseGenerateChecksum>, response: Response<ResponseGenerateChecksum>) {
                    //hiding progress dialog
                    hideLoading()
                    showLog(TAG, "Response : ${response.body()}")
                    if (response.isSuccessful) {
                        if (response.body()!!.success) {
                            val mData = response.body()!!.data
                            if (mData != null) {
                                if (mData.gatewayDetail != null) {

                                    showLog(TAG, "Transaction Id :  ${mData.gatewayDetail.txnId}")
                                    //GozoHelper.toastShort(this@PaymentMethodActivity, " ${mData.gatewayDetail.txnId}")
                                    launchPayUMoney(mData)

                                } else {

                                    toastShort(context, "Transaction data failed!")
                                }

                            } else {
                                if (response.body()!!.message != null) {
                                    toastShort(context, response.body()!!.message)
                                } else {
                                    toastShort(context, "Response data failed!")
                                }

                            }

                        } else {
                            if (response.body()!!.message != null) {
                                toastShort(context, response.body()!!.message)
                            } else {
                                toastShort(context, "Response failed!")
                            }

                        }

                    } else {

                        toastNetworkError(context)

                    }
                }

                override fun onFailure(call: Call<ResponseGenerateChecksum>, t: Throwable) {
                    //hiding progress dialog
                    hideLoading()
                    showLog(TAG, "Failure Response : ${t.message}")
                    toastShort(context, t.message!!)
                }
            })

        }

        private fun launchPayUMoney(mData: com.app.allride.driver.features.auth.login.model.Data) {
            val builder = PayUmoneySdkInitializer.PaymentParam.Builder()
            builder.setAmount(mData.gatewayDetail.amount)                          // Payment amount
                .setTxnId(mData.gatewayDetail.txnId.toString())                     // Transaction ID
                .setPhone(mData.billing.primaryContact.number)                   // User Phone number
                .setProductName(mData.gatewayDetail.description)                   // Product Name or description
                .setFirstName(mData.billing.fullName)                              // User First name
                .setEmail(mData.billing.email)              // User Email ID
                .setsUrl(mData.gatewayDetail.successUrl)     // Success URL (surl)
                .setfUrl(mData.gatewayDetail.failureUrl)     //Failure URL (furl)
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setUdf6("")
                .setUdf7("")
                .setUdf8("")
                .setUdf9("")
                .setUdf10("")
                .setIsDebug(Constants.isPaymentModeDebug)                              // Integration environment - true (Debug)/ false(Production)
                .setKey(mData.gatewayDetail.key)                        // Merchant key
                .setMerchantId(mData.gatewayDetail.merchantId.toString())
            val paymentParam = builder.build()

            paymentParam.setMerchantHash(mData.gatewayDetail.checksum)
            PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, Activity(), R.style.AppTheme_default, false)

        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, context: Context) {
            //super.onActivityResult(requestCode, resultCode, data)
            GozoHelper.showLog(TAG, "request Code : $requestCode  result Code : $resultCode")
            if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == AppCompatActivity.RESULT_OK && data != null) {


                val transactionResponse = data.getParcelableExtra<com.payumoney.core.entity.TransactionResponse>(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE)
                val resultModel = data.getParcelableExtra<ResultModel>(PayUmoneyFlowManager.ARG_RESULT)
                GozoHelper.showLog(TAG, "transactionResponse : $transactionResponse")
                GozoHelper.showLog(TAG, "resultModel : $resultModel")
                if (transactionResponse?.getPayuResponse() != null) {
                    GozoHelper.showLog(TAG, "transactionResponse.transactionStatus : ${transactionResponse.transactionStatus}")
                    GozoHelper.showLog(TAG, "TransactionResponse.TransactionStatus.SUCCESSFUL : ${TransactionResponse.TransactionStatus.SUCCESSFUL}")
                    if (transactionResponse.transactionStatus == com.payumoney.core.entity.TransactionResponse.TransactionStatus.SUCCESSFUL
                        || transactionResponse.transactionStatus == com.payumoney.core.entity.TransactionResponse.TransactionStatus.PG_FORWARD_REQUESTED
                        || transactionResponse.transactionStatus == com.payumoney.core.entity.TransactionResponse.TransactionStatus.FORWARDED
                    ) {
                        //Success Transaction
                        GozoHelper.toastShort(context, "Payment Success.")

                        if (PaymentMethodActivity.mData != null && PaymentMethodActivity.mData!!.bookingId != null) {
                            // Response from Payumoney
                            val payuResponse = transactionResponse.getPayuResponse()

                            // Response from SURl and FURL
                            val merchantResponse = transactionResponse.transactionDetails
                            GozoHelper.showLog(TAG, "transaction :  $merchantResponse")
                            GozoHelper.showLog(TAG, "payuResponse :  $payuResponse")
                            ///GozoHelper.showLog(TAG, "tran $payuResponse---$merchantResponse")
                            val txnId = JSONObject(payuResponse).getJSONObject("result").getString("txnid")

                            if (!TextUtils.isEmpty(txnId)) {
                                //getTransactionStatus(RequestTxnStatus(txnId))
                            }


                        } else {
                            GozoHelper.toastShort(context, "Booking id not found!")
                        }

                        *//*TODO CALL TXN_STATUS API*//*

                    } else {
                        //Failure Transaction
                        GozoHelper.toastShort(context, "Payment Failed!")
                    }


                } else if (resultModel?.error != null) {
                    GozoHelper.showLogError(TAG, "Error response : " + resultModel.error.transactionResponse)
                } else {
                    GozoHelper.showLogError(TAG, "Both objects are null!")
                }
            }

        }*/

//        @SuppressLint("SetTextI18n")
//        fun showRouteDialog(context: Context, title: String, message: String) {
//            val view: View = (context as AppCompatActivity).layoutInflater.inflate(R.layout.dialog_route, null)
//            val dialog = BottomSheetDialog(context)
//            dialog.setContentView(view)
//            dialog.setCanceledOnTouchOutside(false)
//            dialog.show()
//
//            setRegularTypeface(context, view.tv_dialog_title)
//            setRegularTypeface(context, view.tv_dialog_message)
//
//            if (TextUtils.isEmpty(title)) {
//                view.tv_dialog_title.text = ""
//            } else {
//                view.tv_dialog_title.text = title
//            }
//
//            if (TextUtils.isEmpty(message)) {
//                view.tv_dialog_message.text = ""
//            } else {
//                view.tv_dialog_message.text = message
//            }
//
//            view.btn_dialog_cancel.setOnClickListener {
//                dialog.dismiss()
//            }
//        }
//
//
//        fun showMessageAlert(context: Context, msg: String, isDoubleButtonOn: Boolean) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_msg)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//
//            dialog.tv_msg.text = msg
//            if (isDoubleButtonOn) {
//                dialog.findViewById<LinearLayout>(R.id.ll_double_button).visibility = View.VISIBLE
//                dialog.findViewById<Button>(R.id.btn_ok).visibility = View.GONE
//
//            } else {
//                dialog.findViewById<LinearLayout>(R.id.ll_double_button).visibility = View.GONE
//            }
//
//            dialog.findViewById<Button>(R.id.btn_positive).setOnClickListener {
//                dialog.dismiss()
//                (context as AppCompatActivity).finish()
//            }
//            dialog.findViewById<Button>(R.id.btn_negative).setOnClickListener {
//                dialog.dismiss()
//            }
//
//            dialog.findViewById<Button>(R.id.btn_ok).setOnClickListener {
//                dialog.dismiss()
//            }
//
//        }
//
//
//        fun redeemAlert(context: Context, msg: String, isDoubleButtonOn: Boolean) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_msg)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//
//            dialog.tv_msg.text = msg
//            if (isDoubleButtonOn) {
//                dialog.findViewById<LinearLayout>(R.id.ll_double_button).visibility = View.VISIBLE
//                dialog.findViewById<Button>(R.id.btn_ok).visibility = View.GONE
//
//            } else {
//                dialog.findViewById<LinearLayout>(R.id.ll_double_button).visibility = View.GONE
//            }
//
//            dialog.findViewById<Button>(R.id.btn_positive).setOnClickListener {
//                dialog.dismiss()
//                context.startActivity(Intent(context as AppCompatActivity, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//
//                context.finish()
//
//            }
//
//
//
//
//            dialog.findViewById<Button>(R.id.btn_negative).setOnClickListener {
//                dialog.dismiss()
//            }
//
//            dialog.findViewById<Button>(R.id.btn_ok).setOnClickListener {
//                dialog.dismiss()
//            }
//
//        }
//
//
//        @SuppressLint("MissingPermission")
//        fun showCallEmailDialog(context: Context, msg: String, isDoubleButtonOn: Boolean, packageId: String, packageName: String) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_call_email)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//
//            dialog.tv_msg.text = msg
//            dialog.btn_cancel_dialog.visibility = View.VISIBLE
//            if (isDoubleButtonOn) {
//                dialog.findViewById<LinearLayout>(R.id.ll_double_button).visibility = View.VISIBLE
//                dialog.findViewById<Button>(R.id.btn_ok).visibility = View.GONE
//            } else {
//                dialog.findViewById<LinearLayout>(R.id.ll_double_button).visibility = View.GONE
//            }
//
//            dialog.findViewById<Button>(R.id.btn_call_now).setOnClickListener(object : View.OnClickListener {
//                override fun onClick(p0: View?) {
//
//                    val phoneNumber: String = "tel:9051877000"
//
//                    if (GozoRuntimePermission.checkCallPermission(context)) {
//                        val callIntent = Intent(Intent.ACTION_CALL)
//                        callIntent.setData(Uri.parse(phoneNumber))
//                        (context as AppCompatActivity).startActivity(callIntent)
//                    } else {
//                        dialog.dismiss()
//                    }
//                }
//            })
//            //(context as AppCompatActivity).finish()
//
//            dialog.findViewById<Button>(R.id.btn_email_us).setOnClickListener(object : View.OnClickListener {
//                @SuppressLint("IntentReset")
//                override fun onClick(p0: View?) {
//                    /*  try {
//                        val emailIntent = Intent(Intent.ACTION_SEND)
//                        emailIntent.data = Uri.parse("mailto:")
//                        emailIntent.putExtra(Intent.EXTRA_EMAIL, "info@gozocabs.com")
//                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Get Quote for package : $packageName (Package id : $packageId)")
//                        emailIntent.type = "text/plain"
//                        //emailIntent.setPackage("com.google.android.gm")
//                        (context as AppCompatActivity).startActivity(emailIntent)
//                    } catch (e: Exception) {
//                        GozoHelper.toastShort(context, "Please install a email client first ...")
//                        e.printStackTrace()
//                    }*/
//
//                    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:info@gozocabs.com"))
//                    intent.putExtra(Intent.EXTRA_SUBJECT, "Get Quote for package : $packageName (Package id : $packageId)")
//                    intent.putExtra(Intent.EXTRA_TEXT, "")
//                    (context as AppCompatActivity).startActivity(Intent.createChooser(intent, "Choose email client ..."))
//                }
//
//            })
//
//            dialog.findViewById<Button>(R.id.btn_ok).setOnClickListener {
//                dialog.dismiss()
//            }
//            dialog.findViewById<ImageButton>(R.id.btn_cancel_dialog).setOnClickListener {
//                dialog.dismiss()
//            }
//
//
//        }
//
//        @SuppressLint("MissingPermission")
//        fun showCallDialogOnly(context: Context, msg: String, isDoubleButtonOn: Boolean) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_call_email)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//
//            dialog.tv_msg.text = msg
//            dialog.btn_cancel_dialog.visibility = View.GONE
//
//            if (isDoubleButtonOn) {
//                dialog.findViewById<LinearLayout>(R.id.ll_button).visibility = View.VISIBLE
//                dialog.findViewById<Button>(R.id.btn_ok).visibility = View.GONE
//
//            } else {
//                dialog.findViewById<LinearLayout>(R.id.ll_button).visibility = View.GONE
//            }
//
//            dialog.findViewById<Button>(R.id.btn_call).setOnClickListener(object : View.OnClickListener {
//                override fun onClick(p0: View?) {
//
//                    val phoneNumber: String = "tel:9051877000"
//
//                    if (GozoRuntimePermission.checkCallPermission(context)) {
//                        val callIntent = Intent(Intent.ACTION_CALL)
//                        callIntent.setData(Uri.parse(phoneNumber))
//                        (context as AppCompatActivity).startActivity(callIntent)
//                    } else {
//                        dialog.dismiss()
//                    }
//                }
//            })
//
//            dialog.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
//                dialog.dismiss()
//            }
//
//            dialog.findViewById<Button>(R.id.btn_ok).setOnClickListener {
//                dialog.dismiss()
//            }
//            dialog.findViewById<ImageButton>(R.id.btn_cancel_dialog).setOnClickListener {
//                dialog.dismiss()
//            }
//
//        }

//        fun showNetworkErrorAlert(context: Context, isForceToFinish: Boolean) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_internet_error)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//
//            //dialog.tv_msg.text = context.resources.getString(R.string.internet_error_msg)
//
//            dialog.findViewById<Button>(R.id.btn_positive).setOnClickListener {
//                if (isForceToFinish) {
//                    (context as AppCompatActivity).finish()
//                    dialog.dismiss()
//                } else {
//                    dialog.dismiss()
//                }
//            }
//
//        }

//        fun referralShare(context: Context, code: String, msg: String) {
//            val shareIntent = Intent()
//            shareIntent.action = Intent.ACTION_SEND
//            shareIntent.type = "text/plain"
//            shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.text_choose_one))
//            shareIntent.putExtra(
//                Intent.EXTRA_TEXT,
//                "$msg \n\nhttps://www.gozocabs.com/invite/$code"
//            )
//            context.startActivity(
//                Intent.createChooser(
//                    shareIntent,
//                    context.getString(R.string.text_choose_one)
//                )
//            )
//        }

//        fun showDialogReferAlert(context: Context, code: String, msg: String) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_refer_earn)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.setCancelable(false)
//            dialog.show()
//            setMediumTypeface(context, dialog.tv_title_refer)
//            setRegularTypeface(context, dialog.tv_msg_refer)
//
//            dialog.tv_msg_refer.text = msg
//
//            dialog.findViewById<Button>(R.id.btn_share).setOnClickListener {
//                dialog.dismiss()
//                referralShare(context, code, msg)
//                dialog.dismiss()
//            }
//
//            dialog.findViewById<ImageButton>(R.id.btn_close_referral).setOnClickListener {
//                dialog.dismiss()
//
//            }
//
//        }
//
//
//        fun showAuthErrorAlert(context: Context) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_network_error)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//
//            dialog.tv_msg.text = context.resources.getString(R.string.auth_error_msg)
//
//            dialog.findViewById<Button>(R.id.btn_positive).setOnClickListener {
//                dialog.dismiss()
//                (context as AppCompatActivity).finish()
//            }
//
//        }
//
//        fun showPermissionAlert(context: Context) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_permission)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//
//            dialog.findViewById<Button>(R.id.btn_positive).setOnClickListener {
//                context.startActivity(Intent(context, NotificationActivity::class.java))
//                dialog.dismiss()
//            }
//            dialog.findViewById<Button>(R.id.btn_negative).setOnClickListener {
//                dialog.dismiss()
//            }
//        }
//
//        fun showEmailDialog(context: Context, tripType: Int) {
//            val dialog = Dialog(context)
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//            dialog.setContentView(R.layout.dialog_mail_contact_input)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//
//            dialog.findViewById<Button>(R.id.btn_positive).setOnClickListener {
//                when (tripType) {
//                    1 -> context.startActivity(
//                        Intent(
//                            context,
//                            CabsActivity::class.java
//                        ).putExtra("type", 1)
//                    )
//
//                    2 -> context.startActivity(
//                        Intent(
//                            context,
//                            CabsActivity::class.java
//                        ).putExtra("type", 2)
//                    )
//
//                    3 -> context.startActivity(
//                        Intent(
//                            context,
//                            CabsActivity::class.java
//                        ).putExtra("type", 3)
//                    )
//
//                    4 -> context.startActivity(
//                        Intent(
//                            context,
//                            CabsActivity::class.java
//                        ).putExtra("type", 4)
//                    )
//                }
//                dialog.dismiss()
//            }
//            dialog.findViewById<Button>(R.id.btn_negative).setOnClickListener {
//                dialog.dismiss()
//            }
//        }

        /* @SuppressLint("SetTextI18n")
         fun showRatingBottomSheet(context: Context, bookingId: Int, route: List<String>) {
             val view: View = (context as AppCompatActivity).layoutInflater.inflate(R.layout.dialog_rating_alert, null)

             val dialog = BottomSheetDialog(context)
             dialog.setContentView(view)
             dialog.setCanceledOnTouchOutside(false)
             dialog.show()

             view.tv_booking_id.text = "Booking Id : $bookingId"
             view.tv_route.text = "${route[0]} - ${route[1]}"

             view.btn_Rate_now.setOnClickListener {
                 val intent = Intent(context, BookingDetailsActivity::class.java).putExtra("bookingId", bookingId)
                 context.startActivity(intent)
                 dialog.dismiss()
             }

             view.btn_skip.setOnClickListener {
                 mPreference!!.setIsFirstTimeAppLoad(false)
                 dialog.dismiss()
             }
         }*/

        /*fun showDialogRatingAlert(context: Context, bookingId: String, route: String) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_rating_alert)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            dialog.tv_booking_id.text = "Booking Id : $bookingId"
            dialog.tv_route.text = route
            dialog.findViewById<RatingBar>(R.id.rt_bar).setOnClickListener {
             val intent = Intent(context, RatingActivity::class.java)
             (context as AppCompatActivity).startActivity(intent)
             dialog.dismiss()

         }

         dialog.btn_Rate_now.setOnClickListener {
             val intent = Intent(context, RatingActivity::class.java).putExtra("bkg_id", bookingId)
             (context as AppCompatActivity).startActivity(intent)
             dialog.dismiss()
         }

         dialog.btn_skip.setOnClickListener {
             dialog.dismiss()
         }
     }
*/

        fun isConnectedToInternet(context: Context): Boolean {
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            val allNetworks = manager?.allNetworks?.let { it } ?: return false
            allNetworks.forEach { network ->
                val info = manager.getNetworkInfo(network)

                if (info != null) {
                    if (info.state == NetworkInfo.State.CONNECTED) return true
                }
            }
            return false
        }

//        @SuppressLint("SimpleDateFormat")
//        fun getAnyDate(givenDateString: String, dateFormat: String): String {
//            val formatter = SimpleDateFormat(Constants.API_DATE_FORMAT)
//            val mDate: Date = formatter.parse(givenDateString)!!
//            return android.text.format.DateFormat.format(dateFormat, mDate).toString()
//        }
//
//        @SuppressLint("SimpleTimeFormat")
//        fun getAnyTime(givenTimeString: String, timeFormat: String): String {
//            val formatter = SimpleDateFormat(Constants.API_TIME_FORMAT)
//            val mTime: Date = formatter.parse(givenTimeString)!!
//            return android.text.format.DateFormat.format(timeFormat, mTime).toString()
//        }

        /*fun getYearMonth(date: String): String {
            val calendar = Calendar.getInstance()
            val formatter = SimpleDateFormat(Constants.API_DATE_FORMAT)
            val date: Date = formatter.parse(date)!!
            return (calendar.get(Calendar.MONTH) + calendar.get(Calendar.YEAR) * 100).toString()
        }*/

        @SuppressLint("SimpleDateFormat")
        fun getDate(milliSeconds: Long, dateFormat: String): String {
            // Create a DateFormatter object for displaying date in specified format.
            val formatter = SimpleDateFormat(dateFormat)
            // Create a calendar object that will convert the date and time value in milliseconds to date.
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time).toUpperCase()
        }

        fun getTimeFromString(strTime: String, timeAMPM: String): String {
            var time: String? = null
            if (timeAMPM == "AM") {


                time = when (strTime) {
                    "12:00" -> "00:00:00"
                    "12:30" -> "00:30:00"
                    else -> "$strTime:00"
                }

            } else if (timeAMPM == "PM") {

                when (strTime) {
                    "01:00" -> time = "13:00:00"
                    "01:30" -> time = "13:30:00"
                    "02:00" -> time = "14:00:00"
                    "02:30" -> time = "14:30:00"
                    "03:00" -> time = "15:00:00"
                    "03:30" -> time = "15:30:00"
                    "04:00" -> time = "16:00:00"
                    "04:30" -> time = "16:30:00"
                    "05:00" -> time = "17:00:00"
                    "05:30" -> time = "17:30:00"
                    "06:00" -> time = "18:00:00"
                    "06:30" -> time = "18:30:00"
                    "07:00" -> time = "19:00:00"
                    "07:30" -> time = "19:30:00"
                    "08:00" -> time = "20:00:00"
                    "08:30" -> time = "20:30:00"
                    "09:00" -> time = "21:00:00"
                    "09:30" -> time = "21:30:00"
                    "10:00" -> time = "22:00:00"
                    "10:30" -> time = "22:30:00"
                    "11:00" -> time = "23:00:00"
                    "11:30" -> time = "23:30:00"
                    "12:00" -> time = "12:00:00"
                    "12:30" -> time = "12:30:00"
                }


            }
            return time!!
        }

        fun getTwoDaysAfterTimeMillis(): Long {
            val date = Date()
            return date.time + 86400 * 1000 * 2
        }

        fun getCurrentTimeMillis(): Long {
            val date = Date()
            return date.time
        }

        fun getSevenDaysAfterTimeMillis(): Long {
            val date = Date()
            return date.time + 604800000L
        }

        fun getOneDaysAfterTwoDaysTimeMillis(): Long {
            val date = Date()
            return date.time + 86400 * 1000 * 3
        }

//        @SuppressLint("SetTextI18n")
//        fun showFareBreakup(context: Context, data: Bundle) {
//            val view: View = (context as AppCompatActivity).layoutInflater.inflate(R.layout.dialog_fare_bottom_sheet, null)
//
//            if (data.containsKey("cab_category") && data.containsKey("cab_class")) {
//                view.tv_cab_type.text = "${data.getString("cab_category")!!.trim()} \u2022 ${data.getString("cab_class")!!.trim()}"
//                view.tv_cab_type.visibility = View.VISIBLE
//
//            } else {
//                view.tv_cab_type.visibility = View.GONE
//            }
//
//            if (data.containsKey("base_fare")) {
//                view.tv_base_fare.text =
//                    context.resources.getString(R.string.rupee_symbol) + data.getString("base_fare")
//                view.ll_base_fare.visibility = View.VISIBLE
//            }
//
//            if (data.containsKey("discount")) {
//                if (data.containsKey("promo_code")) {
//                    if (data.getString("discount") != null && data.getString("discount") != "0") {
//                        view.tv_discount_title.text = "Discount (Applied ${data.getString("promo_code")})"
//                        view.tv_discount.text = "- ₹${data.getString("discount")}"
//                        view.ll_discount.visibility = View.VISIBLE
//                        view.ll_coins_applied.visibility = View.GONE
//
//                    } else {
//                        view.tv_discount_title.text = "Discount"
//                        view.tv_discount.text = context.resources.getString(R.string.rupee_symbol) + "0"
//                        //view.ll_discount.visibility = View.GONE
//                        view.ll_coins_applied.visibility = View.GONE
//
//                    }
//
//                } else {
//                    //view.tv_discount.text = context.resources.getString(R.string.rupee_symbol) + data.getString("discount")
//                    view.ll_discount.visibility = View.GONE
//                    view.ll_coins_applied.visibility = View.GONE
//
//                }
//
//            } else {
//                //view.tv_discount.text = context.resources.getString(R.string.rupee_symbol) + data.getString("discount")
//                view.ll_discount.visibility = View.GONE
//                view.ll_coins_applied.visibility = View.GONE
//            }
//
//            if (data.containsKey("toll_tax") && data.getString("toll_tax") != null && !data.getString("toll_tax").equals("0") && !data.getString("toll_tax").equals("00")) {
//                view.tv_toll_tax.text =
//                    context.resources.getString(R.string.rupee_symbol) + data.getString("toll_tax")
//                view.ll_toll_tax.visibility = View.VISIBLE
//            }
//            if (data.containsKey("state_tax") && data.getString("state_tax") != null && !data.getString("state_tax").equals("0") && !data.getString("state_tax").equals("00")) {
//                view.tv_state_tax.text =
//                    context.resources.getString(R.string.rupee_symbol) + data.getString("state_tax")
//                view.ll_state_tax.visibility = View.VISIBLE
//            }
//            if (data.containsKey("driver_allowance") && data.getString("driver_allowance") != null && !data.getString("driver_allowance").equals("0") && !data.getString("driver_allowance")
//                    .equals("00")
//            ) {
//
//                view.tv_driver_allowance.text =
//                    context.resources.getString(R.string.rupee_symbol) + data.getString("driver_allowance")
//                view.ll_driver_allowance.visibility = View.VISIBLE
//            }
//            if (data.containsKey("total_fare")) {
//                view.tv_total_fare.text =
//                    context.resources.getString(R.string.rupee_symbol) + data.getString("total_fare")
//            }
//            if (data.containsKey("gst") && data.getString("gst") != null && !data.getString("gst").equals("0") && !data.getString("gst").equals("00")) {
//
//                view.tv_gst_price.text =
//                    context.resources.getString(R.string.rupee_symbol) + data.getString("gst")
//
//                view.ll_gst.visibility = View.VISIBLE
//            }
//
//            if (data.containsKey("night_pickup_allowance") && data.getString("night_pickup_allowance") != null && data.getString("night_pickup_allowance").equals("1")) {
//                setTextOnTextView(view.tv_night_pickup, "Included")
//                view.ll_night_pickup_allowance.visibility = View.VISIBLE
//                view.iv_night_pickup_included.visibility = View.VISIBLE
//                view.iv_night_pickup_not_included.visibility = View.GONE
//            } else {
//                setTextOnTextView(view.tv_night_pickup, "Not Included")
//                view.ll_night_pickup_allowance.visibility = View.VISIBLE
//                view.iv_night_pickup_included.visibility = View.GONE
//                view.iv_night_pickup_not_included.visibility = View.VISIBLE
//            }
//
//            if (data.containsKey("night_drop_allowance") && data.getString("night_drop_allowance") != null && data.getString("night_drop_allowance").equals("1")) {
//
//                setTextOnTextView(view.tv_night_drop, "Included")
//                view.ll_night_drop_allowance.visibility = View.VISIBLE
//                view.iv_night_drop_included.visibility = View.VISIBLE
//                view.iv_night_drop_not_included.visibility = View.GONE
//            } else {
//                setTextOnTextView(view.tv_night_drop, "Not Included")
//                view.ll_night_drop_allowance.visibility = View.VISIBLE
//                view.iv_night_drop_included.visibility = View.GONE
//                view.iv_night_drop_not_included.visibility = View.VISIBLE
//            }
//
//            if (data.containsKey("coins_applied") && data.getString("coins_applied") != null) {
//                view.tv_coins_applied.text = "- ₹${data.getString("coins_applied")}"
//                view.ll_due.visibility = View.GONE
//                view.ll_discount.visibility = View.GONE
//                view.ll_coins_applied.visibility = View.VISIBLE
//
//                if (data.containsKey("discount")) {
//                    if (data.containsKey("promo_code")) {
//                        if (data.getString("discount") != null && data.getString("discount") != "0") {
//                            view.tv_discount_title.text = "Discount (Applied ${data.getString("promo_code")})"
//                            view.tv_discount.text = "- ₹${data.getString("discount")}"
//                            view.ll_discount.visibility = View.VISIBLE
//                            view.ll_coins_applied.visibility = View.GONE
//
//                        } else {
//                            view.tv_discount_title.text = "Discount"
//                            view.tv_discount.text = context.resources.getString(R.string.rupee_symbol) + "0"
//                            //view.ll_discount.visibility = View.GONE
//                            view.ll_coins_applied.visibility = View.GONE
//
//                        }
//
//                    } else {
//                        //view.tv_discount.text = context.resources.getString(R.string.rupee_symbol) + data.getString("discount")
//                        view.ll_discount.visibility = View.GONE
//                        view.ll_coins_applied.visibility = View.GONE
//
//                        if (data.containsKey("due_amount") && data.getString("due_amount") != null) {
//                            view.tv_due.text = "₹${data.getString("due_amount")}"
//                            view.ll_coins_applied.visibility = View.VISIBLE
//                            view.ll_due.visibility = View.VISIBLE
//
//                        } else {
//                            view.ll_due.visibility = View.GONE
//                            view.ll_coins_applied.visibility = View.GONE
//                        }
//                    }
//
//                } else {
//                    view.ll_discount.visibility = View.GONE
//                    view.ll_coins_applied.visibility = View.GONE
//                }
//
//
//            } else {
//                view.ll_coins_applied.visibility = View.GONE
//                view.ll_due.visibility = View.GONE
//                view.ll_discount.visibility = View.VISIBLE
//                view.ll_coins_applied.visibility = View.GONE
//
//            }
//
//            val dialog = BottomSheetDialog(context)
//            dialog.setContentView(view)
//            dialog.show()
//
//            view.btn_sheet_cancel.setOnClickListener {
//                dialog.dismiss()
//            }
//        }
//
//        @SuppressLint("SetTextI18n")
//        fun showBottomPayNow(context: Context, fare: Fare, bookingId: String) {
//            val view: View = (context as AppCompatActivity).layoutInflater.inflate(R.layout.dailog_bottom_paynow, null)
//
//            if (bookingId != null) {
//                view.tv_id.text = "Booking Id: $bookingId"
//            }
//            if (fare.totalAmount != null) {
//                view.tv_total_amounts.text =
//                    context.resources.getString(R.string.rupee_symbol) + fare.totalAmount
//            }
//            if (fare.dueAmount != null) {
//                view.tv_due_amount.text =
//                    context.resources.getString(R.string.rupee_symbol) + fare.dueAmount
//                setTextOnEditText(view.edt_amount, "${fare.dueAmount}")
//            }
//            if (fare.customerPaid != null) {
//                view.tv_paid_amount.text =
//                    context.resources.getString(R.string.rupee_symbol) + fare.customerPaid
//            }
//
//            val dialog = BottomSheetDialog(context)
//            dialog.setContentView(view)
//            dialog.show()
//
//            view.btn_sheet_cancel.setOnClickListener {
//                dialog.dismiss()
//            }
//        }
//
//        fun appShare(context: Context) {
//            val shareIntent = Intent()
//            shareIntent.action = Intent.ACTION_SEND
//            shareIntent.type = "text/plain"
//            shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.text_choose_one))
//            shareIntent.putExtra(
//                Intent.EXTRA_TEXT,
//                "\nLet me recommend you this application\n\n" + "https://play.google.com/store/apps/details?id=com.gozocabs.client" + "\n\n"
//            )
//            context.startActivity(
//                Intent.createChooser(
//                    shareIntent,
//                    context.getString(R.string.text_choose_one)
//                )
//            )
//        }

        fun openPlayStore(context: Context) {
            val uri = Uri.parse("market://details?id=" + context.packageName)
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )
            try {
                context.startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + context.packageName)
                    )
                )
            }

        }

        fun getSymbol(context: Context, symbol: String, textSize: Float, color: Int): Drawable {
            val paint = Paint(ANTI_ALIAS_FLAG)
            paint.textSize = textSize
            paint.color = color
            paint.textAlign = Paint.Align.LEFT
            val baseline = -paint.ascent() // ascent() is negative
            val width = (paint.measureText(symbol) + 2).toInt() // r
            // ound
            val height = (baseline + paint.descent() + 2).toInt()
            val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(image)
            canvas.drawText(symbol, 0.0f, baseline, paint)
            return BitmapDrawable(context.resources, image)
        }

        //to rotate an imageView in clockwise
        fun rotateImage(img: ImageView) {
            img.animate().rotationBy(180f).setDuration(500).setInterpolator(LinearInterpolator())
                .start()
        }

        fun checkPlayServices(context: Context): Boolean {
            val apiAvailability = GoogleApiAvailability.getInstance()
            val resultCode = apiAvailability.isGooglePlayServicesAvailable(context)
            if (resultCode != ConnectionResult.SUCCESS) {
                if (apiAvailability.isUserResolvableError(resultCode)) {
                    apiAvailability.getErrorDialog(
                        context as AppCompatActivity,
                        resultCode,
                        PLAY_SERVICES_RESOLUTION_REQUEST
                    )?.show()
                } else {
                    Log.i(TAG, "This device is not supported.")
                    (context as AppCompatActivity).finish()
                }
                return false
            }
            return true
        }

        fun setData(logs: ArrayList<String>) {

            val nameOfCurrentFunction = Exception().stackTrace[1].methodName
            val nameOfCurrentClass = Exception().stackTrace[1].className
            val lineNo = Exception().stackTrace[1].lineNumber

            val key =
                "ClassName : $nameOfCurrentClass - FunctionName : $nameOfCurrentFunction - LineNo : $lineNo"

            if (htLogs[key] == null) {

            }
            return
        }

        fun setupToolbar(context: Context, title: String) {
            (context as AppCompatActivity).setSupportActionBar(Toolbar(context))
            actionBar = (context as AppCompatActivity).supportActionBar!!
            actionBar.title = title
            actionBar.setDisplayHomeAsUpEnabled(true)
        }


    }

}
