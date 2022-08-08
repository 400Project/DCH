package com.oyatech.dch

import com.google.android.material.tabs.TabLayout


fun setTabBadge(tab:TabLayout,position:Int,listSize: Int){
    tab.getTabAt(position)?.orCreateBadge?.number = listSize
}