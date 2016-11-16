package com.example.administrator.alltest.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.alltest.R;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 参考http://blog.csdn.net/it1039871366/article/details/9849541
 */

public class BluetoothActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;

    @BindView(R.id.list)
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ButterKnife.bind(this);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        //获取蓝牙适配器
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        //显示在listView
        listView.setAdapter(arrayAdapter);
    }
    @OnClick(R.id.turnon)
    public void onClick(){
        if(bluetoothAdapter==null){
            //Device does not support Bluetooth
            return;
        }
        //查询当前蓝牙设备的状态
        if(!bluetoothAdapter.isEnabled()){
            Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,0);
            Toast.makeText(getApplication(),"Turned On",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplication(),"Already On",Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.show)
    public void showClick(){
        //查询已经配对成功了的设备
        arrayAdapter.clear();
        pairedDevices=bluetoothAdapter.getBondedDevices();
        if(pairedDevices.size()>0){
            for(BluetoothDevice device:pairedDevices){
                arrayAdapter.add(device.getName()+"\n"+device.getAddress());
            }
        }
        arrayAdapter.notifyDataSetChanged();
    }

    //设置设备是可发现的
    @OnClick(R.id.visiable)
    public void visiableClick(){
        Intent getVisiable=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisiable,0);
    }

    //关闭蓝牙
    @OnClick(R.id.turnoff)
    public void offClick(){
        bluetoothAdapter.disable();
        Toast.makeText(getApplication(),"Turned off",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.find)
    public void findClick(){
        find();
    }

    //扫描设备
    //注册广播接收对象
    private void find(){
        //创建对象
        final BroadcastReceiver mReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                arrayAdapter.clear();
                String action=intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    arrayAdapter.add(device.getName()+"  "+device.getAddress());
                }
                arrayAdapter.notifyDataSetChanged();
            }
        };
        //完成注册
        IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver,filter);
    }
}
