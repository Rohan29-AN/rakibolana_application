package com.ph03enixc0ders.rakibolanamalagasy.event

import com.ph03enixc0ders.rakibolanamalagasy.entity.teny

interface OnClickItemInterface {

    fun onItemCheckedChanged(item: teny, isCheckBox: Boolean)

    fun onItemClicked(item:teny)
}