package com.example.th2_ltmb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class M001TopicFrg extends Fragment {
    private RecyclerView rvTopic;
    private List<String> topicList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_m001_topic, container, false);
        rvTopic = view.findViewById(R.id.rv_topic);

        // Dữ liệu mẫu
        topicList.add("Con gái");
        topicList.add("Học sinh");
        topicList.add("Gia đình");
        topicList.add("Công sở");
        topicList.add("Vợ chồng");

        // Gán adapter
        rvTopic.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTopic.setAdapter(new TopicAdapter(topicList));

        return view;
    }

    // Adapter đơn giản cho danh sách chủ đề
    static class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicVH> {
        private final List<String> data;

        TopicAdapter(List<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public TopicVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView tv = new TextView(parent.getContext());
            tv.setPadding(16, 24, 16, 24);
            tv.setTextSize(18);
            return new TopicVH(tv);
        }

        @Override
        public void onBindViewHolder(@NonNull TopicVH holder, int position) {
            holder.tv.setText(data.get(position));
            holder.tv.setOnClickListener(v ->
                    ((MainActivity) v.getContext()).gotoM002Screen(data.get(position))
            );
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        static class TopicVH extends RecyclerView.ViewHolder {
            TextView tv;
            TopicVH(@NonNull View itemView) {
                super(itemView);
                tv = (TextView) itemView;
            }
        }
    }
}

