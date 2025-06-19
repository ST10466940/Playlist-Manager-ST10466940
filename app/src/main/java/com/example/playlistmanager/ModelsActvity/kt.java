package com.example.playlistmanager.ModelsActvity;
data class Song(
        val title: String,
        val artist: String,
        var rating: Int,
        val comments: String
)

data class Playlist(
        val name: String,
        val songs: MutableList<Song> = mutableListOf()
)

