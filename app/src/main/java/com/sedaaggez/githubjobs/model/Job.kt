package com.sedaaggez.githubjobs.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Job(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String?,
    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String?,
    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String?,
    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    val createdAt: String?,
    @ColumnInfo(name = "company")
    @SerializedName("company")
    val company: String?,
    @ColumnInfo(name = "company_url")
    @SerializedName("company_url")
    val companyUrl: String?,
    @ColumnInfo(name = "location")
    @SerializedName("location")
    val location: String?,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?,
    @ColumnInfo(name = "how_to_apply")
    @SerializedName("how_to_apply")
    val howToApply: String?,
    @ColumnInfo(name = "company_logo")
    @SerializedName("company_logo")
    val companyLogo: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
