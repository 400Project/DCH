package com.oyatech.dch;

import android.content.Context;
import android.content.SharedPreferences;

public class DepartmentPreference  {
    private Context context;
    private SharedPreferences dpPreference;
    public DepartmentPreference(Context context){
        this.context = context;
        dpPreference = context.getSharedPreferences(context.getString(R.string.log_In_pref),Context.MODE_PRIVATE);
    }

   public void saveDepartment(String department){
        SharedPreferences.Editor editor =dpPreference.edit();
        editor.putString(context.getString(R.string.department),department);
        editor.apply();
   }
   public void clearDepartment(){
        SharedPreferences.Editor editor =dpPreference.edit();
        editor.clear();
        editor.apply();
   }

   public String getDepartment(){
    return   dpPreference.getString(context.getString(R.string.department),"" );
   }

}
