package com.example.moduledb.controlDB.initData.viewState

import com.example.moduledb.controlDB.data.local.entities.MDbPOIs
import com.example.services.data.response.lines.ListLines
import com.example.services.data.response.macroRegions.MacroRegions
import com.example.services.data.response.pointsRecharge.PORecharge

data class MainViewState(
    val pointsOfInterest: List<MDbPOIs> = emptyList(),
    val pointsOfRecharge: List<PORecharge> = emptyList(),
    val macroRegions: List<MacroRegions> = emptyList(),
    val linesByMacroRegion: List<ListLines> = emptyList()
)