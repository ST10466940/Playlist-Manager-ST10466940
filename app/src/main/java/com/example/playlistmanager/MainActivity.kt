import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    companion object {
        val sampleSongs = listOf(
            Song("Song A", "Artist A", 4, "Rap"),
            Song("Song B", "Artist B", 1, "Dance song"),
            Song("Song C", "Artist C", 2, "Best Love Song"),
            Song("Song D", "Artist D", 3, "Mem")
        )
        val playlists = mutableListOf<Playlist>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnAddToPlaylist).setOnClickListener {
            val intent = Intent(this, AddToPlaylistActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnViewDetails).setOnClickListener {
            val intent = Intent(this, DetailedViewActivity::class.java)
            startActivity(intent)
        }
    }
}