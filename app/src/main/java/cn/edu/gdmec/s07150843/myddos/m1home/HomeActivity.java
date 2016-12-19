package cn.edu.gdmec.s07150843.myddos.m1home;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import cn.edu.gdmec.s07150843.myddos.R;
import cn.edu.gdmec.s07150843.myddos.m1home.adapter.HomeAdapter;

public class HomeActivity extends AppCompatActivity {
    /**声明GridView，该控件类是于ListView*/
    private GridView gv_home;
    /**存储手机防盗密码的sp*/
    private SharedPreferences msharedPreferences;
    /**设备管理员*/
    private DevicePolicyManager policyManager;
    /**申请权限*/
    private ComponentName componentName;
    private long mExitTime;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
       msharedPreferences=getSharedPreferences("config",MODE_PRIVATE);

       gv_home=(GridView)findViewById(R.id.gv_home);
       gv_home.setAdapter(new HomeAdapter(HomeActivity.this));
       gv_home.setOnClickListener(new AdapterView.OnItemClickListener(){

           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch (position){
                   case 0:
                       if(isSetUpPassword()){
                           showInterPswdDialog();
                       }else{
                           showSetUpPswdDialog();
                       }
                       break;
                   case 1:
                       startActivity(SecurityPhoneActivity.class);
                       break;
                   case 2:
                       startActivity(AppManagerActivity.class);
                       break;
                   case 3:
                       startActivity(VirusScanActivity.class);
                       break;
                   case 4:
                       startActivity(CacheClearListActivity.class);
                       break;
                   case 5:
                       startActivity(ProcessManagerActivity.class);
                       break;
                   case 6:
                       startActivity(TrafficMonitoringActivity.class);
                       break;
                   case 7:
                       startActivity(AdvancedToolsActivity.class);
                       break;
                   case 8:
                       startActivity(SettingsActivity.class);
                       break;
               }
           }
       });
       policyManager=(DevicePolicyManager)getSystemService(DEVICE_POLICY_SERVICE);
       componentName=new ComponentName(this,MyDeviceAdminReciever.class);
       boolean active=policyManager.isAdminActive(componentName);
       if (!active){
           Intent intent=new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
           intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,componentName);
           intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"获取超级管理员权限，用户远程锁屏和清除数据");
           startActivity(intent);
       }
    }
    private void

    public boolean isSetUpPassword() {
        return setUpPassword;
    }
}
