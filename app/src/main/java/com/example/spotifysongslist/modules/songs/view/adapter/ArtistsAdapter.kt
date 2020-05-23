package com.example.spotifysongslist.modules.songs.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifysongslist.R
import com.example.spotifysongslist.modules.songs.model.Artist

class ArtistsAdapter(private val songsListener: ArtistsListener): RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {

    private var listSongs = ArrayList<Artist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_song, parent, false))

    override fun getItemCount() = listSongs.size

    override fun onBindViewHolder(holder: ArtistsAdapter.ViewHolder, position: Int) {
        val song = listSongs[position]
        holder.setData(song)
        holder.itemView.setOnClickListener {
            songsListener.onSongClicked(song, position)
        }
    }

    fun updateData(data: List<Artist>) {
        listSongs.clear()
        listSongs.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private lateinit var artist: Artist
        private val tvSongName: TextView = itemView.findViewById(R.id.tvItemSongName)

        fun setData(artist: Artist) {
            this.artist = artist

            tvSongName.text = artist.name
        }

    }

}