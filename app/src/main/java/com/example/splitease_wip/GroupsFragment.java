package com.example.splitease_wip;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupsFragment extends Fragment {

    private RecyclerView recyclerView;
    private GroupAdapter adapter;
    private ArrayList<String>groupList;

    public GroupsFragment() {
        // Required empty public constructor
    }



    public static GroupsFragment newInstance(String param1, String param2) {
        GroupsFragment fragment = new GroupsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_groups, container, false);

        recyclerView = root.findViewById(R.id.recycler_view_groups);
        adapter = new GroupAdapter(groupList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new GroupAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String selectedGroup = groupList.get(position);
                // Communicate the selected group to the activity
               openDetails(selectedGroup);
            }
        });

        return root;
    }

    private void openDetails(String selectedGroup) {
    }
}
//    private void showAddGroupDialog() {
//        // Show a dialog to add group name and members
//        // After adding group, update UI accordingly
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Add New Group");
//
//        // Set up the input
//        final EditText input = new EditText(getContext());
//        input.setInputType(InputType.TYPE_CLASS_TEXT);
//        builder.setView(input);
//
//        // Set up the buttons
//        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String groupName = input.getText().toString().trim();
//                if (!groupName.isEmpty()) {
//                    addGroupToDatabase(groupName);
//                } else {
//                    Toast.makeText(getContext(), "Group name cannot be empty", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        builder.show();
//    }
//
//    private void addGroupToDatabase(String groupName) {
//        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
//
//        boolean inserted = dbHelper.insertGroup(groupName);
//        if (inserted) {
//            // Update UI - Maybe refresh the group list
//            Toast.makeText(getContext(), "Group added successfully", Toast.LENGTH_SHORT).show();
//            checkAndDisplayGroups();
//        } else {
//            Toast.makeText(getContext(), "Failed to add group", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private boolean checkIfGroupsExist() {
//        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        // Query to count the number of rows in the table
//        String query = "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_NAME;
//
//        Cursor cursor = db.rawQuery(query, null);
//        int count = 0;
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                count = cursor.getInt(0); // Get the count from the first column of the first row
//            }
//            cursor.close();
//        }
//
//        return count > 0; // If count is greater than 0, groups exist; otherwise, return false
//    }
//
//
//    private void setupRecyclerView() {
//        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        ArrayList<String> groupList = new ArrayList<>();
//
//        // Query the database to retrieve group names
//        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.COL_NAME}, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            String groupName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
//            groupList.add(groupName);
//        }
//        cursor.close();
//
//        // Create and set up the RecyclerView adapter
//        GroupAdapter adapter = new GroupAdapter(groupList);
//        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerViewGroups.setAdapter(adapter);
//    }
//    private void checkAndDisplayGroups() {
//        boolean groupsExist = checkIfGroupsExist();
//
//        if (groupsExist) {
//            textNoGroups.setVisibility(View.GONE);
//            textNoGroups2.setVisibility(View.GONE);
//            textNoGroups3.setVisibility(View.GONE);
//            textNoGroups4.setVisibility(View.GONE);
//            recyclerViewGroups.setVisibility(View.VISIBLE);
//            setupRecyclerView(); // Call setupRecyclerView to display the groups
//        } else {
//            textNoGroups.setVisibility(View.VISIBLE);
//            recyclerViewGroups.setVisibility(View.GONE);
//        }
//    }



//
//  textNoGroups = view.findViewById(R.id.text_no_groups);
//          textNoGroups2 = view.findViewById(R.id.text_no_groups2);
//          textNoGroups3 = view.findViewById(R.id.text_no_groups3);
//          textNoGroups4 = view.findViewById(R.id.text_no_groups4);
//          recyclerViewGroups = view.findViewById(R.id.recycler_view_groups);
//          fabAddGroup = view.findViewById(R.id.fab_add_group);
//
//          fabAddGroup.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        showAddGroupDialog();
//        }
//        });
//
//        boolean groupsExist = checkIfGroupsExist();
//        if (groupsExist) {
//        textNoGroups.setVisibility(View.GONE);
//        textNoGroups2.setVisibility(View.GONE);
//        textNoGroups3.setVisibility(View.GONE);
//        textNoGroups4.setVisibility(View.GONE);
//        recyclerViewGroups.setVisibility(View.VISIBLE);
//        setupRecyclerView();
//        } else {
//        textNoGroups.setVisibility(View.VISIBLE);
//        recyclerViewGroups.setVisibility(View.GONE);
//        }
