package com.example.th2_ltmb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class M002StoryFrg extends Fragment {
    private RecyclerView rvStory;
    private TextView tvTopicName;
    private String topicName;

    public M002StoryFrg(String topicName) {
        this.topicName = topicName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_m002_story, container, false);
        tvTopicName = view.findViewById(R.id.tv_topic_name);
        rvStory = view.findViewById(R.id.rv_story);

        tvTopicName.setText("Chủ đề: " + topicName);

        // Dữ liệu truyện mẫu
        List<StoryEntity> stories = new ArrayList<>();
        stories.add(new StoryEntity("Vừa đúng vừa sai", "Một anh chàng nói chuyện với cô gái..."));
        stories.add(new StoryEntity("Học sinh lười", "Trong lớp, thầy giáo hỏi học sinh..."));
        stories.add(new StoryEntity("Chuyện hài về " + topicName, "Một câu chuyện cười vui nhộn."));

        rvStory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvStory.setAdapter(new StoryAdapter(stories));

        return view;
    }

    // Adapter hiển thị danh sách truyện
    static class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryVH> {
        private final List<StoryEntity> list;

        StoryAdapter(List<StoryEntity> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public StoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new StoryVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StoryVH holder, int position) {
            StoryEntity s = list.get(position);
            holder.title.setText(s.getTitle());
            holder.desc.setText(s.getDesc());

            // 👇 Thêm sự kiện click để mở chi tiết
            holder.itemView.setOnClickListener(v -> {
                ((MainActivity) v.getContext()).gotoM003Screen(new ArrayList<>(list), list.get(position));
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        static class StoryVH extends RecyclerView.ViewHolder {
            TextView title, desc;
            StoryVH(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(android.R.id.text1);
                desc = itemView.findViewById(android.R.id.text2);
            }
        }
    }
}
