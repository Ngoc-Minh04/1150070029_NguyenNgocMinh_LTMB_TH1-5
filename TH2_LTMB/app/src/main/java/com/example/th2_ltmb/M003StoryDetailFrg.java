package com.example.th2_ltmb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class M003StoryDetailFrg extends Fragment {
    private TextView tvTitle, tvContent, btnBack;
    private ArrayList<StoryEntity> listStory;
    private int currentIndex;
    private float startX;

    public M003StoryDetailFrg(ArrayList<StoryEntity> listStory, StoryEntity story) {
        this.listStory = listStory;
        this.currentIndex = listStory.indexOf(story);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_m003_story_detail, container, false);

        tvTitle = view.findViewById(R.id.tv_title);
        tvContent = view.findViewById(R.id.tv_content);
        btnBack = view.findViewById(R.id.btn_back);

        // Hiển thị truyện đầu tiên
        showStory(currentIndex);

        // ⚙️ Xử lý nút "Quay lại"
        btnBack.setOnClickListener(v -> {
            // Gọi hàm trong MainActivity để quay lại M002
            ((MainActivity) getActivity()).backToM001Screen(); // Nếu bạn có M002 riêng, có thể thay bằng hàm backToM002Screen()
        });

        // 👆 Nếu bạn muốn quay lại đúng M002 thay vì M001, hãy tạo thêm:
        // ((MainActivity)getActivity()).gotoM002Screen("Tên chủ đề");

        // Xử lý vuốt trái/phải đổi truyện
        view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    return true;
                case MotionEvent.ACTION_UP:
                    float endX = event.getX();
                    if (startX - endX > 150) nextStory();      // Vuốt trái
                    else if (endX - startX > 150) prevStory(); // Vuốt phải
                    return true;
            }
            return false;
        });

        return view;
    }

    private void showStory(int index) {
        if (index < 0 || index >= listStory.size()) return;
        StoryEntity story = listStory.get(index);
        tvTitle.setText(story.getTitle());
        tvContent.setText(story.getDesc());
    }

    private void nextStory() {
        if (currentIndex < listStory.size() - 1) {
            currentIndex++;
            showStory(currentIndex);
        }
    }

    private void prevStory() {
        if (currentIndex > 0) {
            currentIndex--;
            showStory(currentIndex);
        }
    }
}
