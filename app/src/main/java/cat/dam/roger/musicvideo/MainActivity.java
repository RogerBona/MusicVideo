package cat.dam.roger.musicvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    //Variables
    private MediaPlayer mediaPlayer;
    private Button btn_musica, btn_local, btn_remot;
    private VideoView mVideoView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarvistes();//Funcio a on cargarem les vistes

        controlMusica();//Funcio per executar la musica
        controlVideo();//Funcio per executar el video
        controlvideoremot();//Funcio per obrir el video en remot
    }

    //Aqui carguem les vistes que farem servir
    private void cargarvistes() {
        btn_musica = findViewById(R.id.btn_musica);
        btn_local = this.findViewById(R.id.btn_local);
        btn_remot = this.findViewById(R.id.btn_remot);
        mVideoView = findViewById(R.id.mVideoView);
        webView = findViewById(R.id.webView);
    }

    private void controlMusica() {
        btn_musica.setText(R.string.sound_player);//Posem nom al boto
        mediaPlayer = MediaPlayer.create(this, R.raw.bzrp2);//Linck de a on es esta

        btn_musica.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {//Si esta funcionant la musica entrarem
                mediaPlayer.pause(); //Pararem la musica
                btn_musica.setText(R.string.sound_player);//canviem el nom del boto
                btn_musica.setAlpha(1f); //Canvien  la transperencia per saber que es pot apretar
            }
            else {
                mediaPlayer.start();
                btn_musica.setText(R.string.listening);
                btn_musica.setAlpha(.5f); //Canvien  la transperencia
            }
        });
    }

    private void controlVideo() {
        btn_local.setText(R.string.video_player);//Posem nom al boto
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bzrp);//Linck de a on es esta
        mVideoView.setVideoURI(video);
        mVideoView.setMediaController(new MediaController(this));
        // control quan finalitza el video
        mVideoView.setOnCompletionListener(mp -> {
            btn_local.setText(R.string.video_player);//Posarem de nom al boto de reprodueix video
            btn_local.setAlpha(1f); // Canvien  la transperencia per saber que es pot apretar
        });
        //control botó
        btn_local.setOnClickListener(v -> {
            if (mVideoView.isPlaying()) { //Si esta funcionant
                mVideoView.pause(); // Pausem el video
                btn_local.setText(R.string.paused);//Posarem de nom al boto que esta  parat
                btn_local.setAlpha(1f); // Canvien  la transperencia per saber que es pot apretar
            }
            else {
                mVideoView.requestFocus();
                mVideoView.start();
                btn_local.setText(R.string.showing);
                btn_local.setAlpha(.5f); // amb transparència
            }
        });
    }

    private void controlvideoremot() {

        btn_remot.setOnClickListener(v -> {
            btn_local.setText(R.string.video_player);
            webView.loadUrl("https://www.youtube.com/watch?v=FsF3o9XNcN8&ab_channel=Bizarrap");
        });
    }

}