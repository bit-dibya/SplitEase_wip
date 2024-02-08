package com.example.splitease_wip;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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


    // Table name constant
    private static final String TABLE_NAME = "groups_table";

    // Column name constant
    private static final String COL_NAME = "group_name";

    private TextView textNoGroups;
    private TextView textNoGroups2;
    private TextView textNoGroups3;
    private TextView textNoGroups4;
    private RecyclerView recyclerViewGroups;
    private FloatingActionButton fabAddGroup;

    public GroupsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupsFragment newInstance(String param1, String param2) {
        GroupsFragment fragment = new GroupsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_groups, container, false);

        textNoGroups = view.findViewById(R.id.text_no_groups);
        textNoGroups2 = view.findViewById(R.id.text_no_groups2);
        textNoGroups3 = view.findViewById(R.id.text_no_groups3);
        textNoGroups4 = view.findViewById(R.id.text_no_groups4);
        recyclerViewGroups = view.findViewById(R.id.recycler_view_groups);
        fabAddGroup = view.findViewById(R.id.fab_add_group);

        fabAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddGroupDialog();
            }
        });

        boolean groupsExist = checkIfGroupsExist();
        if (groupsExist) {
            textNoGroups.setVisibility(View.GONE);
            textNoGroups2.setVisibility(View.GONE);
            textNoGroups3.setVisibility(View.GONE);
            textNoGroups4.setVisibility(View.GONE);
            recyclerViewGroups.setVisibility(View.VISIBLE);
            setupRecyclerView();
        } else {
            textNoGroups.setVisibility(View.VISIBLE);
            recyclerViewGroups.setVisibility(View.GONE);
        }



        return view;
    }
    private void showAddGroupDialog() {
        // Show a dialog to add group name and members
        // After adding group, update UI accordingly
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add New Group");

        // Set up the input
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String groupName = input.getText().toString().trim();
                if (!groupName.isEmpty()) {
                    addGroupToDatabase(groupName);
                } else {
                    Toast.makeText(getContext(), "Group name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void addGroupToDatabase(String groupName) {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());

        boolean inserted = dbHelper.insertGroup(groupName);
        if (inserted) {
            // Update UI - Maybe refresh the group list
            Toast.makeText(getContext(), "Group added successfully", Toast.LENGTH_SHORT).show();
            checkAndDisplayGroups();
        } else {
            Toast.makeText(getContext(), "Failed to add group", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkIfGroupsExist() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query to count the number of rows in the table
        String query = "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        int count = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0); // Get the count from the first column of the first row
            }
            cursor.close();
        }

        return count > 0; // If count is greater than 0, groups exist; otherwise, return false
    }


    private void setupRecyclerView() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<String> groupList = new ArrayList<>();

        // Query the database to retrieve group names
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.COL_NAME}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String groupName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
            groupList.add(groupName);
        }
        cursor.close();

        // Create and set up the RecyclerView adapter
        GroupAdapter adapter = new GroupAdapter(groupList);
        recyclerViewGroups.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewGroups.setAdapter(adapter);
    }
    private void checkAndDisplayGroups() {
        boolean groupsExist = checkIfGroupsExist();

        if (groupsExist) {
            textNoGroups.setVisibility(View.GONE);
            textNoGroups2.setVisibility(View.GONE);
            textNoGroups3.setVisibility(View.GONE);
            textNoGroups4.setVisibility(View.GONE);
            recyclerViewGroups.setVisibility(View.VISIBLE);
            setupRecyclerView(); // Call setupRecyclerView to display the groups
        } else {
            textNoGroups.setVisibility(View.VISIBLE);
            recyclerViewGroups.setVisibility(View.GONE);
        }
    }


}