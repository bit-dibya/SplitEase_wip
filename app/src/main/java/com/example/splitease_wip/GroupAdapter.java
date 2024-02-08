package com.example.splitease_wip;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private ArrayList<String> groupList;
    private ArrayList<String> groupList2;
    private AdapterView.OnItemClickListener listener;


    public GroupAdapter(ArrayList<String> groupList) {
        this.groupList = groupList;

    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = (AdapterView.OnItemClickListener) listener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        String groupName = groupList.get(position);
        holder.bind(groupName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v, groupName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewGroupName;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGroupName = itemView.findViewById(R.id.text_group_name);
        }

        public void bind(String groupName) {
            textViewGroupName.setText(groupName);
        }
    }
    private void showPopupMenu(View view, String groupName) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.group_list_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_add_edit_member) {// Open fragment to add/edit members
                    openAddEditMemberFragment(groupName);
                    return true;
                } else if (itemId == R.id.menu_edit_delete_group) {// Open fragment to edit/delete group
                    openEditDeleteGroupFragment(groupName);
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();
    }
    private void openAddEditMemberFragment(String groupName) {
        // Open fragment to add/edit members
        // Pass the group name to the fragment
        // Example: AddEditMemberFragment.newInstance(groupName)
    }

    private void openEditDeleteGroupFragment(String groupName) {
        // Open fragment to edit/delete group
        // Pass the group name to the fragment
        // Example: EditDeleteGroupFragment.newInstance(groupName)
    }


}
