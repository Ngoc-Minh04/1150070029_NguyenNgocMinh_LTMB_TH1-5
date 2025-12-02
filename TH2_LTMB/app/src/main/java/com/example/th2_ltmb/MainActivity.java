package com.example.th2_ltmb;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String lastTopicName; // lưu tên chủ đề để back đúng M002

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFrg(new SplashFrg());
    }

    // Hàm hiển thị Fragment với hiệu ứng mượt
    private void showFrg(Fragment frg) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.ln_main, frg, null)
                .commit();
    }

    // M000 → M001 (danh sách chủ đề)
    public void gotoM001Screen() {
        showFrg(new M001TopicFrg());
    }

    // M001 → M002 (danh sách truyện)
    public void gotoM002Screen(String topicName) {
        lastTopicName = topicName; // lưu lại chủ đề
        showFrg(new M002StoryFrg(topicName));
    }

    // M002 → M003 (chi tiết truyện)
    public void gotoM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {
        showFrg(new M003StoryDetailFrg(listStory, story));
    }

    // Quay lại M001 (chủ đề)
    public void backToM001Screen() {
        gotoM001Screen();
    }

    // Quay lại M002 (danh sách truyện của chủ đề trước đó)
    public void backToM002Screen() {
        if (lastTopicName != null) {
            showFrg(new M002StoryFrg(lastTopicName));
        } else {
            gotoM001Screen();
        }
    }
}
