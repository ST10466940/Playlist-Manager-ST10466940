import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetailedViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        val btnShowSongs = findViewById<Button>(R.id.btnShowSongs)
        val btnCalculateAverage = findViewById<Button>(R.id.btnCalculateAverage)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        val spinnerPlaylists = findViewById<Spinner>(R.id.spinnerPlaylists)

        // Setup playlist spinner
        val playlistNames = MainActivity.playlists.map { it.name }
        spinnerPlaylists.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, playlistNames)

        btnShowSongs.setOnClickListener {
            val selectedPlaylistIndex = spinnerPlaylists.selectedItemPosition
            if (selectedPlaylistIndex >= 0 && selectedPlaylistIndex < MainActivity.playlists.size) {
                val playlist = MainActivity.playlists[selectedPlaylistIndex]
                val output = StringBuilder()

                for (song in playlist.songs) {
                    output.append("""
                        Title: ${song.title}
                        Artist: ${song.artist}
                        Rating: ${song.rating}
                        Comments: ${song.comments}
                        ----------------------
                    """.trimIndent()).append("\n\n")
                }

                tvOutput.text = if (output.isNotEmpty()) output.toString() else "No songs in this playlist"
            }
        }

        btnCalculateAverage.setOnClickListener {
            val selectedPlaylistIndex = spinnerPlaylists.selectedItemPosition
            if (selectedPlaylistIndex >= 0 && selectedPlaylistIndex < MainActivity.playlists.size) {
                val playlist = MainActivity.playlists[selectedPlaylistIndex]

                if (playlist.songs.isEmpty()) {
                    tvOutput.text = "No songs in this playlist"
                    return@setOnClickListener
                }

                var totalRating = 0
                for (song in playlist.songs) {
                    totalRating += song.rating
                }

                val average = totalRating.toDouble() / playlist.songs.size
                tvOutput.text = "Average rating: %.2f".format(average)
            }
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}