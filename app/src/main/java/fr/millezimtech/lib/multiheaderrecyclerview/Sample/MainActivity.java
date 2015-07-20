package fr.millezimtech.lib.multiheaderrecyclerview.Sample;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import fr.millezimtech.lib.designlibrary.R;
import fr.millezimtech.lib.multiheaderrecyclerview.Library.Adapter.MultiHeaderNumberedAdapter;
import fr.millezimtech.lib.multiheaderrecyclerview.Library.MultiHeaderRecyclerView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    MultiHeaderRecyclerView recyclerView;

    CoordinatorLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initToolbar();
//        initInstances();
        initRecycler();
    }

    private void initRecycler() {
        recyclerView = (MultiHeaderRecyclerView) findViewById(R.id.my_recycler_view);

//        final GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
//        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new MarginDecoration(this));
//        recyclerView.setLayoutManager(mLayoutManager);
//
//
////        NumberedAdapter adapter = new NumberedAdapter(20);
//        recyclerView.setAdapter(adapter);

//        View headerPicture = LayoutInflater.from(this).inflate(R.layout.header_picture, recyclerView, false);
//
//        headerPicture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Header", Toast.LENGTH_SHORT).show();
//            }
//        });

//        final HeaderNumberedAdapter headerAdapter  = new HeaderNumberedAdapter(headerPicture, 30);
        final MultiHeaderNumberedAdapter headerAdapter = new MultiHeaderNumberedAdapter(ApplicationUtils.getRandomItemList(50, this, recyclerView),this);
        recyclerView.setAdapter(headerAdapter);

//        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return headerAdapter.isHeader(position) ? mLayoutManager.getSpanCount() : 1;
//            }
//        });


    }


//    private void initToolbar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//    }
//
//    private void initInstances() {
//
//        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
//
//        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
//        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.toolbar_text);
//        collapsingToolbarLayout.setTitle("Chateau Neuf du Pape");
//    }


}
