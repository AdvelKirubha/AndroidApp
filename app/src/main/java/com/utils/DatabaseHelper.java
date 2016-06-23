package com.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rajasingh on 17/4/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ACSDB";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_SITES_TABLE = "CREATE TABLE sitestable ("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"siteid TEXT UNIQUE, "+"userid TEXT, "+"sitename TEXT, "
                +"deviceid TEXT, "+"ip TEXT, "+"issubsites TEXT, "+"clientid TEXT, "+"customerid TEXT )";

        String CREATE_SUBSITES_TABLE = "CREATE TABLE subsites ("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"subsiteid TEXT UNIQUE, "+"subsitename TEXT, "
                +"parentid TEXT, "+"deviceid TEXT, "+"ip TEXT, "+"userid TEXT )";

        String CREATE_TABLE_ATLONA_DEVICES = "CREATE TABLE atlonadevices ("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"atid TEXT UNIQUE, "+"name TEXT, "+"text TEXT, "
                +"parentid TEXT, "+"isdiv TEXT, "+"ipaddress TEXT, "+"siteid TEXT, "+"subsiteid TEXT, "
                +"deviceid TEXT,  "+"issubsites TEXT) ";

        String CREATE_DEVICES_TABLE = "CREATE TABLE devices ("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"configid TEXT UNIQUE, "+"userid TEXT, "
                +"deviceid TEXT, "+"inouttype TEXT, "+"device TEXT, "+"brand TEXT, "+"model TEXT, "
                +"protocol TEXT, "+"contdev TEXT, "+"inpdeviceId TEXT, "+"devicename TEXT, "+"devicenumber TEXT, "
                +"currowid TEXT, "+"curinp TEXT, "+"portnum TEXT, "+"isactive TEXT, "+"createdon TEXT, "
                +"modifiedon TEXT, "+"siteid TEXT, "+"isdiv TEXT )";

        String CREATE_TABLE_INPCOMMANDS = "CREATE TABLE inpcommands ("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"commandid TEXT, "
                +"indeviceid TEXT, "
                +"poweron TEXT, "
                +"poweroff TEXT, "
                +"menuon TEXT, "
                +"menuoff TEXT, "
                +"up TEXT, "
                +"down TEXT, "
                +"right TEXT, "
                +"left TEXT, "
                +"enter TEXT, "
                +"zoomin TEXT"
                +"zoomout TEXT, "
                +"muteon TEXT, "
                +"muteoff TEXT, "
                +"volumeplus TEXT, "
                +"volumeminus TEXT, "
                +"computer1 TEXT,"
                +"computer2 TEXT, "
                +"video1 TEXT, "
                +"svideo1 TEXT, "
                +"compositevideo TEXT, "
                +"componentvideo TEXT, "
                +"analogrgb TEXT, "
                +"digitalrgb TEXT, "
                +"input1 TEXT, "
                +"input2 TEXT, "
                +"input3 TEXT, "
                +"input4 TEXT, "
                +"hdmi1 TEXT, "
                +"hdmi2 TEXT, "
                +"bauderate TEXT, "
                +"termination TEXT, "
                +"hexascii TEXT, "
                +"baud TEXT )";

        String CREATE_TABLE_OUTCOMMANDS = "CREATE TABLE outcommands ("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"commandid TEXT, "
                +"RS232 TEXT, "
                +"baud TEXT, "
                +"Parity TEXT, "
                +"StartBit TEXT, "
                +"StopBit TEXT, "
                +"carriagereturn TEXT, "
                +"LineFeed TEXT, "
                +"IR TEXT, "
                +"IP TEXT, "
                +"PJLINK TEXT, "
                +"MenuON TEXT"
                +"up TEXT, "
                +"down TEXT, "
                +"right TEXT, "
                +"left TEXT, "
                +"Enter TEXT, "
                +"MenuOFF TEXT,"
                +"computer1 TEXT, "
                +"computer2 TEXT, "
                +"video1 TEXT, "
                +"HDEMI1 TEXT, "
                +"VolumePlus TEXT, "
                +"Volumeminus TEXT, "
                +"MuteOn TEXT, "
                +"PowerOn TEXT, "
                +"PowerOff TEXT, "
                +"HEXASCII TEXT, "
                +"OutDeviceID TEXT, "
                +"MuteOff TEXT, "
                +"HDMI1 TEXT, "
                +"HDMI2 TEXT, "
                +"HDMI3 TEXT, "
                +"Svideo TEXT, "
                +"Svideo1 TEXT, "
                +"Input1 TEXT, "
                +"Input2 TEXT, "
                +"Input3 TEXT, "
                +"Input4 TEXT, "
                +"Zoomin TEXT, "
                +"Zoomout TEXT, "
                +"moutOff TEXT, "
                +"componentvideo TEXT, "
                +"compositevideo TEXT, "
                +"analogRGB TEXT, "
                +"analogRGB1 TEXT, "
                +"analogRGB2 TEXT, "
                +"digitalRGB TEXT, "
                +"inputswitching TEXT, "
                +"CommandOn TEXT )";


        db.execSQL(CREATE_TABLE_INPCOMMANDS);
        db.execSQL(CREATE_SITES_TABLE);
        db.execSQL(CREATE_SUBSITES_TABLE);
        db.execSQL(CREATE_DEVICES_TABLE);
        db.execSQL(CREATE_TABLE_ATLONA_DEVICES);
        db.execSQL(CREATE_TABLE_OUTCOMMANDS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS sitestable");
        db.execSQL("DROP TABLE IF EXISTS subsites");
        db.execSQL("DROP TABLE IF EXISTS devices");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_INPCOMMANDS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ATLONA_DEVICES);
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_OUTCOMMANDS);

        this.onCreate(db);

    }

    public static final String TABLE_SITES = "sitestable";
    public static final String KEY_SITEID = "siteid";
    public static final String KEY_USERID = "userid";
    public static final String KEY_SITENAME = "sitename";
    public static final String KEY_DEVICEID = "deviceid";
    public static final String KEY_IP = "ip";
    public static final String KEY_ISSUBSITES = "issubsites";
    public static final String KEY_CLIENTID = "clientid";
    public static final String KEY_CUSTOMERID = "customerid";

    public static final String TABLE_SUBSITES = "subsites";
    public static final String KEY_SUBSITEID = "subsiteid";
    public static final String KEY_SUBSITENAME = "subsitename";
    public static final String KEY_SUBPARENTID = "parentid";
    public static final String KEY_SUBDEVICEID = "deviceid";
    public static final String KEY_SUBIP = "ip";
    public static final String KEY_SUBUSERID = "userid";

    public static final String TABLE_ATLONA_DEVICES = "atlonadevices";
    public static final String KEY_ATLONA_ID = "atid";
    public static final String KEY_ATLONA_NAME = "name";
    public static final String KEY_ATLONA_TEXT = "text";
    public static final String KEY_ATLONA_PARENTID = "parentid";
    public static final String KEY_ATLONA_ISDIV = "isdiv";
    public static final String KEY_ATLONA_IPADDRESS = "ipaddress";
    public static final String KEY_ATLONA_SITEID = "siteid";
    public static final String KEY_ATLONA_SUBSITEID = "subsiteid";
    public static final String KEY_ATLONA_DEVICEID = "deviceid";
    public static final String KEY_ATLONA_ISSUBSITES = "issubsites";

    public static final String TABLE_DEVICES = "devices";
    public static final String KEY_CONFIGID = "configid";
    public static final String KEY_DEVUSERID = "userid";
    public static final String KEY_DEVICE_ID = "deviceid";
    public static final String KEY_INPOUP_TYPE = "inouttype";
    public static final String KEY_DEVICE = "device";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_MODEL = "model";
    public static final String KEY_DEVPROTOCOL = "protocol";
    public static final String KEY_CONTROLDEVICE = "contdev";
    public static final String KEY_INPDEVICEID = "inpdeviceId";
    public static final String KEY_DEVICENAME = "devicename";
    public static final String KEY_DEVICENUMBER = "devicenumber";
    public static final String KEY_CURROWID = "currowid";
    public static final String KEY_CURINPUT = "curinp";
    public static final String KEY_PORTNUMBER = "portnum";  //ags
    public static final String KEY_CURISACTIVE = "isactive";
    public static final String KEY_CREATEDON = "createdon";
    public static final String KEY_MODIFIEDON = "modifiedon";
    public static final String KEY_DEVSITEID = "siteid";
    public static final String KEY_ISDIV = "isdiv";


    public static final String TABLE_INPCOMMANDS = "inpcommands";
    public static final String KEY_COMMANDID = "commandid";
    public static final String KEY_INDEVICEID = "indeviceid";
    public static final String KEY_POWERON = "poweron";
    public static final String KEY_POWEROFF = "poweroff";
    public static final String KEY_MENUON = "menuon";
    public static final String KEY_MENUOFF = "menuoff";
    public static final String KEY_UP = "up";
    public static final String KEY_DOWN = "down";
    public static final String KEY_RIGHT = "right";
    public static final String KEY_LEFT = "left";
    public static final String KEY_ENTER = "enter";
    public static final String KEY_ZOOMIN = "zoomin";
    public static final String KEY_ZOOMOUT = "zoomout";
    public static final String KEY_MUTEON = "muteon";
    public static final String KEY_MUTEOFF = "muteoff";
    public static final String KEY_VOLUMEPLUS = "volumeplus";
    public static final String KEY_VOLUMEMINUS = "volumeminus";
    public static final String KEY_COMPUTER1 = "computer1";
    public static final String KEY_COMPUTER2 = "computer2";
    public static final String KEY_VIDEO1 = "video1";
    public static final String KEY_SVIDEO1 = "svideo1";
    public static final String KEY_COMPOSITEVIDEO = "compositevideo";
    public static final String KEY_COMPONENTVIDEO = "componentvideo";
    public static final String KEY_ANALOGRGB = "analogrgb";
    public static final String KEY_DIGITALRGB = "digitalrgb";
    public static final String KEY_INPUT1 = "input1";
    public static final String KEY_INPUT2 = "input2";
    public static final String KEY_INPUT3 = "input3";
    public static final String KEY_INPUT4 = "input4";
    public static final String KEY_HDMI1 = "hdmi1";
    public static final String KEY_HDMI2 = "hdmi2";
    public static final String KEY_BAUDERATE = "bauderate";
    public static final String KEY_TERMINATION = "termination";
    public static final String KEY_HEXASCII = "hexascii";
    public static final String KEY_BAUD = "baud";

    public static final String TABLE_OUTCOMMANDS = "outcommands";
    public static final String KEY_OCOMMANDID = "commandid";
    public static final String KEY_ODEVICEID = "OutDeviceID";
    public static final String KEY_ORS232 = "RS232";
    public static final String KEY_OPARITY = "Parity";
    public static final String KEY_OSTARTBIT = "StartBit";
    public static final String KEY_OSTOPBIT = "StopBit";
    public static final String KEY_OCARRETURN = "carriagereturn";
    public static final String KEY_OLINEFEED = "LineFeed";
    public static final String KEY_OIR = "IR";
    public static final String KEY_OIP = "IP";
    public static final String KEY_OPJLINK = "PJLINK";
    public static final String KEY_OMENUON = "MenuON";
    public static final String KEY_OUP = "up";
    public static final String KEY_ODOWN = "down";
    public static final String KEY_ORIGHT = "right";
    public static final String KEY_OLEFT = "left";
    public static final String KEY_OENTER = "Enter";
    public static final String KEY_OMENUOFF = "MenuOFF";
    public static final String KEY_OCOMPONE = "computer1";
    public static final String KEY_OCOMPTWO = "computer2";
    public static final String KEY_OVIDEONE = "video1";
    public static final String KEY_OHDEMI = "HDEMI1";
    public static final String KEY_OVOLUMEPLUS = "VolumePlus";
    public static final String KEY_OVOLUMEMINUS = "Volumeminus";
    public static final String KEY_OMUTEON = "MuteOn";
    public static final String KEY_OPOWERON = "PowerOn";
    public static final String KEY_OPOWEROFF = "PowerOff";
    public static final String KEY_OHEXASCII = "HEXASCII";
    public static final String KEY_OMUTEOFF = "MuteOff";
    public static final String KEY_OHDMIONE = "HDMI1";
    public static final String KEY_OHDMITWO = "HDMI2";
    public static final String KEY_OHDMITHREE = "HDMI3";
    public static final String KEY_OSVIDEO = "Svideo";
    public static final String KEY_OSVIDEONE = "Svideo1";
    public static final String KEY_OINPUTONE = "Input1";
    public static final String KEY_OINPUTTWO = "Input2";
    public static final String KEY_OINPUTTHREE = "Input3";
    public static final String KEY_OINPUTFOUR = "Input4";
    public static final String KEY_OZOOMIN = "Zoomin";
    public static final String KEY_OZOOMOUT = "Zoomout";
    public static final String KEY_OMOUTOFF = "moutOff";
    public static final String KEY_OCOMPONENT = "componentvideo";
    public static final String KEY_OCOMPOSITE = "compositevideo";
    public static final String KEY_OANALOGRGB = "analogRGB";
    public static final String KEY_OANALOGRGB1 = "analogRGB1";
    public static final String KEY_OANALOGRGB2 = "analogRGB2";
    public static final String KEY_ODIGITALRGB = "digitalRGB";
    public static final String KEY_OINPSWITCHING = "inputswitching";
    public static final String KEY_OCOMMANDON = "CommandOn";
    public static final String KEY_OBAUD = "baud";


    public void addSites(String siteid, String userId, String siteName, String deviceId, String ip, String isSubsites,
                         String clientId, String customerId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SITEID,siteid);
        values.put(KEY_USERID,userId);
        values.put(KEY_SITENAME,siteName);
        values.put(KEY_DEVICEID,deviceId);
        values.put(KEY_IP,ip);
        values.put(KEY_ISSUBSITES,isSubsites);
        values.put(KEY_CLIENTID,clientId);
        values.put(KEY_CUSTOMERID,customerId);
        db.insertWithOnConflict(TABLE_SITES,KEY_SITEID,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void addsubSites(String ssiteid, String ssitename, String sparentid, String sdeviceid, String sip, String suserid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SUBSITEID,ssiteid);
        values.put(KEY_SUBSITENAME,ssitename);
        values.put(KEY_SUBPARENTID,sparentid);
        values.put(KEY_SUBDEVICEID,sdeviceid);
        values.put(KEY_SUBIP,sip);
        values.put(KEY_SUBUSERID,suserid);
        db.insertWithOnConflict(TABLE_SUBSITES, KEY_SUBSITEID, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void addAtlonaDevices(AtlonaDevices atlona){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ATLONA_ID, atlona.getAtlona_id());
        values.put(KEY_ATLONA_NAME,atlona.getAtlona_name());
        values.put(KEY_ATLONA_TEXT, atlona.getAtlona_text());
        values.put(KEY_ATLONA_PARENTID, atlona.getAtlona_parentId());
        values.put(KEY_ATLONA_ISDIV, atlona.atlona_isDiv);
        values.put(KEY_ATLONA_IPADDRESS, atlona.getAtlona_ipaddress());
        values.put(KEY_ATLONA_SITEID, atlona.getAtlona_siteId());
        values.put(KEY_ATLONA_SUBSITEID, atlona.getAtlona_subsiteId());
        values.put(KEY_ATLONA_DEVICEID, atlona.getAtlona_deviceId());
        values.put(KEY_ATLONA_ISSUBSITES, atlona.getAtlona_issubsites());
        db.insertWithOnConflict(TABLE_ATLONA_DEVICES,KEY_ATLONA_ID,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }


    public void addDevices(String configId, String userId, String deviceId, String inoutType, String device,
                           String brand, String model, String protocol, String contDevice,
                           String inDeviceid, String deviceName,String deviceNumber, String currowId, String curInp, String portNumber, String isActive,
                           String createdOn, String modifiedOn, String siteId, String isDiv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CONFIGID,configId);
        values.put(KEY_DEVUSERID,userId);
        values.put(KEY_DEVICE_ID,deviceId);
        values.put(KEY_INPOUP_TYPE,inoutType);
        values.put(KEY_DEVICE,device);
        values.put(KEY_BRAND,brand);
        values.put(KEY_MODEL,model);
        values.put(KEY_DEVPROTOCOL,protocol);
        values.put(KEY_CONTROLDEVICE,contDevice);
        values.put(KEY_INPDEVICEID,inDeviceid);
        values.put(KEY_DEVICENAME,deviceName);
        values.put(KEY_DEVICENUMBER,deviceNumber);
        values.put(KEY_CURROWID,currowId);
        values.put(KEY_CURINPUT,curInp);      //ags

        values.put(KEY_PORTNUMBER,portNumber);

        values.put(KEY_CURISACTIVE,isActive);
        values.put(KEY_CREATEDON,createdOn);
        values.put(KEY_MODIFIEDON,modifiedOn);
        values.put(KEY_DEVSITEID,siteId);
        values.put(KEY_ISDIV,isDiv);
        db.insertWithOnConflict(TABLE_DEVICES,KEY_CONFIGID,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public  String retAtlonacount(){
        String count = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String sQuery = "Select count(*) from "+TABLE_ATLONA_DEVICES;
        Cursor cursor = db.rawQuery(sQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            count = String.valueOf(cursor.getInt(0));
        }
        cursor.close();
        db.close();
        return count;
    }

    public void clearDevices(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_DEVICES);
        db.close();
    }

    public void addInputCommands(InputDeviceCommands commands){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMMANDID,commands.getCommandId());
        values.put(KEY_INDEVICEID,commands.getInpDevId());
        values.put(KEY_POWERON,commands.getPowerOn());
        values.put(KEY_POWEROFF,commands.getPowerOff());
        values.put(KEY_MENUON,commands.getMenuOn());
        values.put(KEY_MENUOFF,commands.getMenuOff());
        values.put(KEY_UP,commands.getUp());
        values.put(KEY_DOWN,commands.getDown());
        values.put(KEY_RIGHT,commands.getRight());
        values.put(KEY_LEFT,commands.getLeft());
        values.put(KEY_ENTER,commands.getEnter());
        values.put(KEY_ZOOMIN,commands.getZoomIn());
        values.put(KEY_ZOOMOUT,commands.getZoomOut());
        values.put(KEY_MUTEON,commands.getMuteOn());
        values.put(KEY_MUTEOFF,commands.getMuteOff());
        values.put(KEY_VOLUMEPLUS,commands.getVolumePlus());
        values.put(KEY_VOLUMEMINUS,commands.getVolumeMinus());
        values.put(KEY_COMPUTER1,commands.getCompOne());
        values.put(KEY_COMPUTER2,commands.getCompTwo());
        values.put(KEY_VIDEO1,commands.getVidOne());
        values.put(KEY_SVIDEO1,commands.getSvidOne());
        values.put(KEY_COMPOSITEVIDEO,commands.getCompositeVideo());
        values.put(KEY_COMPONENTVIDEO,commands.getComponentVideo());
        values.put(KEY_ANALOGRGB,commands.getAnalogRgb());
        values.put(KEY_DIGITALRGB,commands.getDigitalRgb());
        values.put(KEY_INPUT1,commands.getInputOne());
        values.put(KEY_INPUT2,commands.getInputTwo());
        values.put(KEY_INPUT3,commands.getInputThree());
        values.put(KEY_INPUT4,commands.getInputFour());
        values.put(KEY_HDMI1,commands.getHdmiOne());
        values.put(KEY_HDMI2,commands.getHdmiTwo());
        values.put(KEY_BAUDERATE,commands.getBauderate());
        values.put(KEY_TERMINATION,commands.getTermination());
        values.put(KEY_HEXASCII,commands.getHexAscii());
        values.put(KEY_BAUD,commands.getBaud());
        db.insertWithOnConflict(TABLE_INPCOMMANDS,KEY_INDEVICEID,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();

    }

    public void addOutputCommands(OutputDeviceCommands commands){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OCOMMANDID,commands.getCommandId());
        values.put(KEY_ORS232,commands.getRs232());
        values.put(KEY_OBAUD,commands.getBaud());
        values.put(KEY_OPARITY,commands.getParity());
        values.put(KEY_OSTARTBIT,commands.getStartBit());
        values.put(KEY_OSTOPBIT,commands.getStopBit());
        values.put(KEY_OCARRETURN,commands.getCarriageReturn());
        values.put(KEY_OLINEFEED,commands.getLineFeed());
        values.put(KEY_OIR,commands.getIr());
        values.put(KEY_OIP,commands.getIp());
        values.put(KEY_OPJLINK,commands.getPjLink());
        values.put(KEY_OMENUON,commands.getMenuOn());
        values.put(KEY_OUP,commands.getUp());
        values.put(KEY_ODOWN,commands.getDown());
        values.put(KEY_ORIGHT,commands.getRight());
        values.put(KEY_OLEFT,commands.getLeft());
        values.put(KEY_OENTER,commands.getEnter());
        values.put(KEY_OMENUOFF,commands.getMenuOff());
        values.put(KEY_OCOMPONE,commands.getCompOne());
        values.put(KEY_OCOMPTWO,commands.getCompTwo());
        values.put(KEY_OVIDEONE,commands.getVidOne());
        values.put(KEY_OHDEMI,commands.getHdemiOne());
        values.put(KEY_OVOLUMEPLUS,commands.getVolumePlus());
        values.put(KEY_OVOLUMEMINUS,commands.getVolumeMinus());
        values.put(KEY_OMUTEON,commands.getMuteOn());
        values.put(KEY_OPOWERON,commands.getPowerOn());
        Log.d("POWERON__>ADD",commands.getPowerOn());
        values.put(KEY_OPOWEROFF,commands.getPowerOff());
        values.put(KEY_OHEXASCII,commands.getHexAscii());
        values.put(KEY_ODEVICEID,commands.getOutDeviceId());
        values.put(KEY_OMUTEOFF,commands.getMuteOff());
        values.put(KEY_OHDMIONE,commands.getHdmiOne());
        values.put(KEY_OHDMITWO,commands.getHdmiTwo());
        values.put(KEY_OHDMITHREE,commands.getHdmiThree());
        values.put(KEY_OSVIDEO,commands.getsVideo());
        values.put(KEY_OSVIDEONE,commands.getsVideoOne());
        values.put(KEY_OINPUTONE,commands.getInputOne());
        values.put(KEY_OINPUTTWO,commands.getInputTwo());
        values.put(KEY_OINPUTTHREE,commands.getInputThree());
        values.put(KEY_OINPUTFOUR,commands.getInputFour());
        values.put(KEY_OZOOMIN,commands.getZoomIn());
        values.put(KEY_OZOOMOUT,commands.getZoomOut());
        values.put(KEY_OMOUTOFF,commands.getmOutOff());
        values.put(KEY_OCOMPONENT,commands.getComponentVideo());
        values.put(KEY_OCOMPOSITE,commands.getCompositeVideo());
        values.put(KEY_OANALOGRGB,commands.getAnalogRgb());
        values.put(KEY_OANALOGRGB1,commands.getAnalogRgbOne());
        values.put(KEY_OANALOGRGB2,commands.getAnalogRgbTwo());
        values.put(KEY_ODIGITALRGB,commands.getDigitalRgb());
        values.put(KEY_OINPSWITCHING,commands.getInputSwitching());
        values.put(KEY_OCOMMANDON,commands.getCommandOn());
        db.insertWithOnConflict(TABLE_OUTCOMMANDS,KEY_ODEVICEID,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public String getNoOfoutputDevices(){
        String noOfOutDevices = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String sQuery = "SELECT COUNT(*) FROM "+TABLE_DEVICES+" WHERE "+KEY_INPOUP_TYPE+" = '2'";
        Cursor cursor = db.rawQuery(sQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                noOfOutDevices = String.valueOf(cursor.getInt(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noOfOutDevices;
    }

    public ArrayList<String> getOutPutDevices(){
        ArrayList<String> output = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sQuery = "SELECT "+KEY_INPDEVICEID+" , "+KEY_DEVICENUMBER+" "+" FROM "+TABLE_DEVICES+" WHERE "+KEY_INPOUP_TYPE+" = '2'";
        Cursor cursor = db.rawQuery(sQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                String outDevId = cursor.getString(cursor.getColumnIndex(KEY_INPDEVICEID));
               String deviceNumber = cursor.getString(cursor.getColumnIndex(KEY_DEVICENUMBER));
                output.add(outDevId);
                output.add(deviceNumber);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return output;



    }

    public ArrayList<String> getOutPutDevicesName(){
        ArrayList<String> output = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sQuery = "SELECT "+KEY_INPDEVICEID+" , "+KEY_DEVICENUMBER+" "+" FROM "+TABLE_DEVICES+" WHERE "+KEY_INPOUP_TYPE+" = '2'";
        Cursor cursor = db.rawQuery(sQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
               // String outDevId = cursor.getString(cursor.getColumnIndex(KEY_INPDEVICEID));
                String deviceNumber = cursor.getString(cursor.getColumnIndex(KEY_DEVICENUMBER));
              //  output.add(outDevId);
                output.add(deviceNumber);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return output;



    }


    public ArrayList<OutputDeviceCommands> getOutDeviceCommands(String outputDeviceId){

        ArrayList<OutputDeviceCommands> deviceCmds = new ArrayList<OutputDeviceCommands>();
        SQLiteDatabase db = this.getWritableDatabase();
//        String sQuery = "SELECT "+KEY_OPOWERON+","+KEY_OPOWEROFF+","+KEY_OVOLUMEPLUS+","+KEY_OVOLUMEMINUS+","
//                +KEY_OMUTEON+" FROM "+TABLE_OUTCOMMANDS+" WHERE "+KEY_ODEVICEID+" ='"+outputDeviceId+"'";
        String sQuery = "SELECT * FROM "+TABLE_OUTCOMMANDS+" WHERE "+KEY_ODEVICEID+" = '"+outputDeviceId+"'";
        Log.d("SQUERY-->",sQuery);
        Cursor cursor = db.rawQuery(sQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                OutputDeviceCommands outDevCmd = new OutputDeviceCommands();
                String powerOn = cursor.getString(cursor.getColumnIndex(KEY_OPOWERON));
                Log.d("Poweron-->",powerOn);
                String powerOff = cursor.getString(cursor.getColumnIndex(KEY_OPOWEROFF));
                Log.d("Poweroff-->",powerOff);
                String volPlus = cursor.getString(cursor.getColumnIndex(KEY_OVOLUMEPLUS));
                Log.d("VolumePlus-->",volPlus);
                String volMinus = cursor.getString(cursor.getColumnIndex(KEY_OVOLUMEMINUS));
                Log.d("VolumeMinus-->",volMinus);
                String muteOn = cursor.getString(cursor.getColumnIndex(KEY_OMUTEON));
                Log.d("Muteon-->",muteOn);
                outDevCmd.setPowerOn(powerOn);
                outDevCmd.setPowerOff(powerOff);
                outDevCmd.setVolumePlus(volPlus);
                outDevCmd.setVolumeMinus(volMinus);
                outDevCmd.setMuteOn(muteOn);
                deviceCmds.add(outDevCmd);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return deviceCmds;
    }

    public ArrayList<Sites> getSites(){
        ArrayList<Sites> gSites = new ArrayList<Sites>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT "+KEY_SITEID+","+KEY_SITENAME+" FROM "+TABLE_SITES;
        Cursor cursor = db.rawQuery(sqlQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                String siteId = cursor.getString(cursor.getColumnIndex(KEY_SITEID));
                String siteName = cursor.getString(cursor.getColumnIndex(KEY_SITENAME));
                if(!gSites.contains(siteId)){
                    gSites.add(new Sites(siteName,siteId));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gSites;
    }

    public ArrayList<Subsites> getSubsites(){
        ArrayList<Subsites> gsSites = new ArrayList<Subsites>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT "+KEY_SUBSITEID+","+KEY_SUBSITENAME+" FROM "+TABLE_SUBSITES;
        Cursor cursor = db.rawQuery(sqlQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                String siteId = cursor.getString(cursor.getColumnIndex(KEY_SUBSITEID));
                String siteName = cursor.getString(cursor.getColumnIndex(KEY_SUBSITENAME));
                if(!gsSites.contains(siteId)){
                    gsSites.add(new Subsites(siteId,siteName));
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gsSites;
    }

    public String getIssubsites(String userId){
        String gisSites = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT "+KEY_ISSUBSITES+" FROM "+TABLE_SITES+" WHERE "+KEY_USERID+" = '"+userId+"'";
        Cursor cursor = db.rawQuery(sqlQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                String siteId = cursor.getString(cursor.getColumnIndex(KEY_ISSUBSITES));

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gisSites;
    }

    public String getUserId(String subsiteId){
        String gisSites = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT "+KEY_USERID+" FROM "+TABLE_SUBSITES+" WHERE "+KEY_SUBSITEID+" = '"+subsiteId+"'";
        Cursor cursor = db.rawQuery(sqlQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                gisSites = cursor.getString(cursor.getColumnIndex(KEY_USERID));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gisSites;
    }

    /*
    Get the Configured Devices List from the Devices Table.
     */

    public ArrayList<String> getDevices(){
        ArrayList<String> availDevices = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT "+KEY_DEVICE+" FROM "+TABLE_DEVICES;
        Cursor cursor = db.rawQuery(sqlQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                String devices = cursor.getString(cursor.getColumnIndex(KEY_DEVICE));
                if(!availDevices.contains(devices)){
                    availDevices.add(devices);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return availDevices;
    }


    public String getDevicesName(String port){
        String availDevices = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT "+KEY_DEVICE+" FROM "+TABLE_DEVICES +" WHERE "+KEY_INPOUP_TYPE+" ='2' "+" and "+KEY_PORTNUMBER+" ='"+port+"'";
        Cursor cursor = db.rawQuery(sqlQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                 availDevices = cursor.getString(cursor.getColumnIndex(KEY_DEVICE));
//                if(!availDevices.contains(devices)){
//                    availDevices.add(devices);
                //}
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return availDevices;
    }


    /*
    Get the IP Address from the Atlona Devices table.
     */

    public String getAtlonaIp(){
        String atlonaIp = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String sQuery = "SELECT "+KEY_ATLONA_IPADDRESS+" FROM "+TABLE_ATLONA_DEVICES;
        Cursor cursor = db.rawQuery(sQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                atlonaIp = cursor.getString(cursor.getColumnIndex(KEY_ATLONA_IPADDRESS));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return atlonaIp;
    }

//    public ArrayList<Devices> getInputOutputType(){
//        ArrayList<Devices> availDeviceId = new ArrayList<Devices>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        String sqlQuery = "SELECT "+KEY_INPOUP_TYPE+" FROM "+TABLE_DEVICES;
//        Cursor cursor = db.rawQuery(sqlQuery,null);
//        if(cursor != null){
//            cursor.moveToFirst();
//            do{
//                Devices devices = new Devices();
//                String inpoptype = cursor.getString(cursor.getColumnIndex(KEY_INPOUP_TYPE));
//               // String iodeviceId = cursor.getString(cursor.getColumnIndex(KEY_INPDEVICEID));
//
//              //  devices.setInpDeviceId(iodeviceId);
//                devices.setInoutType(inpoptype);
//
//                Log.d("inpoptypeeeees",inpoptype);
//                //Log.d("iodeviceId",iodeviceId);
//
//                availDeviceId.add(devices);
//
//                /*if(!availDeviceId.contains(iodeviceId)){
//                    availDeviceId.add(inpoptype);
//                    availDeviceId.add(iodeviceId);
//                }*/
//            }while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return availDeviceId;
//
//    }
    public ArrayList<Devices> getDeviceId(){
        ArrayList<Devices> availDeviceId = new ArrayList<Devices>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT "+KEY_INPDEVICEID+" , "+KEY_INPOUP_TYPE+" FROM "+TABLE_DEVICES;
        Cursor cursor = db.rawQuery(sqlQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                Devices devices = new Devices();
                String inpoptype = cursor.getString(cursor.getColumnIndex(KEY_INPOUP_TYPE));
                String iodeviceId = cursor.getString(cursor.getColumnIndex(KEY_INPDEVICEID));

                devices.setInpDeviceId(iodeviceId);
                devices.setInoutType(inpoptype);

                Log.d("inpoptype",inpoptype);
                Log.d("iodeviceId",iodeviceId);

                availDeviceId.add(devices);

                /*if(!availDeviceId.contains(iodeviceId)){
                    availDeviceId.add(inpoptype);
                    availDeviceId.add(iodeviceId);
                }*/
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return availDeviceId;

    }

    public ArrayList<Devices> getHDMI(){
        ArrayList<Devices> hdmiList = new ArrayList<Devices>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sQuery = "SELECT "+KEY_CURINPUT+","+KEY_INPOUP_TYPE+","+KEY_PORTNUMBER+","+KEY_DEVICENUMBER+","+KEY_CONFIGID+","+KEY_DEVICE+","+KEY_INPDEVICEID+" FROM "+TABLE_DEVICES;
        Cursor cursor = db.rawQuery(sQuery,null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                Devices devices = new Devices();
                String configId = cursor.getString(cursor.getColumnIndex(KEY_CONFIGID));
                String deviceName = cursor.getString(cursor.getColumnIndex(KEY_DEVICE));
                String curInp = cursor.getString(cursor.getColumnIndex(KEY_CURINPUT));
                String inOutType = cursor.getString(cursor.getColumnIndex(KEY_INPOUP_TYPE));
                String portNum = cursor.getString(cursor.getColumnIndex(KEY_PORTNUMBER));
                String deviceNumber = cursor.getString(cursor.getColumnIndex(KEY_DEVICENUMBER));
                String inpDeviceId = cursor.getString(cursor.getColumnIndex(KEY_INPDEVICEID));
                devices.setDevConfigId(configId);
                devices.setDevName(deviceName);
                devices.setCurInput(curInp);
                devices.setInoutType(inOutType);
                devices.setPortNum(portNum);
                devices.setDeviceNumber(deviceNumber);
                devices.setInpDeviceId(inpDeviceId);
                hdmiList.add(devices);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return hdmiList;
    }


   /* public ArrayList<GetCmds> getCommands(String configid){
        ArrayList<GetCmds> gCommand = new ArrayList<GetCmds>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sQuery = "SELECT "+KEY_INPDEVICEID+","+KEY_INPOPTYPE+ " FROM "+TABLE_DEVICES+ " WHERE "+KEY_CONFIGID+" = '"+configid+"'";
        Cursor cursor = db.rawQuery(sQuery, null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                String deviceid = cursor.getString(cursor.getColumnIndex(KEY_INPDEVICEID));
                String isinput = cursor.getString(cursor.getColumnIndex(KEY_INPOPTYPE));
                gCommand.add(new GetCmds(deviceid, isinput));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gCommand;
    }
*/




    public String retCommand(String mkey, String value){
        String cmd = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String key = "KEY_"+mkey.toUpperCase();
        String sQuery = "SELECT "+mkey+" FROM "+TABLE_INPCOMMANDS+" WHERE "+KEY_INDEVICEID+" = '"+value+"'";
        Cursor cursor = db.rawQuery(sQuery, null);
        if(cursor != null){
            cursor.moveToFirst();
            do{
                cmd = cursor.getString(cursor.getColumnIndex(key));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cmd;
    }


}
