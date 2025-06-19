import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddToPlaylistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_playlist)

        val spinnerSongs = findViewById<Spinner>(R.id.spinnerSongs)
        val spinnerPlaylists = findViewById<Spinner>(R.id.spinnerPlaylists)
        val etNewPlaylist = findViewById<EditText>(R.id.etNewPlaylist)
        val btnCreatePlaylist = findViewById<Button>(R.id.btnCreatePlaylist)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val etComments = findViewById<EditText>(R.id.etComments)
        val etArtistInfo = findViewById<EditText>(R.id.etArtistInfo)
        val btnAddSong = findViewById<Button>(R.id.btnAddSong)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Setup song spinner
        val songTitles = MainActivity.sampleSongs.map { it.title }
        spinnerSongs.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, songTitles)

        // Setup playlist spinner
        updatePlaylistSpinner(spinnerPlaylists)

        btnCreatePlaylist.setOnClickListener {
            val playlistName = etNewPlaylist.text.toString()
            if (playlistName.isNotEmpty()) {
                MainActivity.playlists.add(Playlist(playlistName))
                updatePlaylistSpinner(spinnerPlaylists)
                etNewPlaylist.text.clear()
                Toast.makeText(this, "Playlist created!", Toast.LENGTH_SHORT).show()
            }
        }

        btnAddSong.setOnClickListener {
            val selectedSongIndex = spinnerSongs.selectedItemPosition
            val selectedPlaylistIndex = spinnerPlaylists.selectedItemPosition

            if (selectedPlaylistIndex >= 0 && selectedPlaylistIndex < MainActivity.playlists.size) {
                val song = MainActivity.sampleSongs[selectedSongIndex].copy(
                    rating = ratingBar.rating.toInt(),
                    comments = etComments.text.toString()
                )

                MainActivity.playlists[selectedPlaylistIndex].songs.add(song)

                // Update artist information (simplified - in a real app you'd have a proper artist model)
                Toast.makeText(this, "Song added to playlist!", Toast.LENGTH_SHORT).show()

                // Clear inputs
                etComments.text.clear()
                etArtistInfo.text.clear()
                ratingBar.rating = 0f
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun updatePlaylistSpinner(spinner: Spinner) {
        val playlistNames = MainActivity.playlists.map { it.name }
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, playlistNames)
    }
}