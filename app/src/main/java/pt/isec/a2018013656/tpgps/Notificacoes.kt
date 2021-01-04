package pt.isec.a2018013656.tpgps

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Notificacoes : AppCompatActivity() {
    var mHourEditText: EditText? = null
    var mMinuteEditText: EditText? = null
    var mSetAlarmButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificacoes_layout)

        mHourEditText = findViewById<View>(R.id.hour_edit_text) as EditText
        mMinuteEditText = findViewById<View>(R.id.minute_edit_text) as EditText
        mSetAlarmButton = findViewById<View>(R.id.set_alarm_button) as Button
        mSetAlarmButton!!.setOnClickListener {
            val hour = mHourEditText!!.text.toString().toInt()
            val minute = mMinuteEditText!!.text.toString().toInt()
            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_HOUR, hour)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, minute)
            if (hour <= 24 && minute <= 60) {
                startActivity(intent)
            }
        }
    }
}