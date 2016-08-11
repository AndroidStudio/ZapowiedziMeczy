package com.footballscore.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.footballscore.R;
import com.footballscore.activities.FixturesActivity;
import com.footballscore.adapters.FixturesAdapter;
import com.footballscore.models.FixtureModel;
import com.footballscore.requests.FixtureRequest;
import com.footballscore.utils.Constants;
import com.footballscore.utils.DividerItemDecoration;
import com.network.library.NetworkManager;
import com.network.library.NetworkManagerCallbacks;

import java.util.ArrayList;

public class FixturesFragment extends Fragment {
    public static final String TAG = "PreviewsFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fixture_fragment, container, false);

        FixturesAdapter competitionsAdapter = new FixturesAdapter(getActivity());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getDrawable(R.drawable.separator)));
        recyclerView.setAdapter(competitionsAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.onCreateFixturesList();
    }

    private void onCreateFixturesList() {
        FixturesActivity activity = (FixturesActivity) getActivity();

        String id = activity.getCompetitionModel().getId();
        int fixtureType = getArguments().getInt(Constants.FIXTURE_TYPE);

        FixtureRequest fixtureRequest = new FixtureRequest(id, fixtureType) {
            @Override
            public void onResult(ArrayList<FixtureModel> result) throws Exception {
                View view = getView();
                if (view == null) {
                    return;
                }

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                FixturesAdapter adapter = (FixturesAdapter) recyclerView.getAdapter();
                adapter.setFixtureModelList(result);

                if (result.size() < 1) {
                    view.findViewById(R.id.noResultsLayout).setVisibility(View.VISIBLE);
                }
            }
        };

        NetworkManager networkManager = new NetworkManager(getActivity(), true);
        networkManager.setDelay(500);
        networkManager.addRequest(fixtureRequest);
        networkManager.setNetworkManagerCallbacks(networkManagerCallbacks);
        networkManager.execute();
    }

    private final NetworkManagerCallbacks networkManagerCallbacks = new NetworkManagerCallbacks() {

        @Override
        public void onStart() throws Exception {
            View view = getView();
            if (view != null) {
                view.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onError(String error) throws Exception {
            View view = getView();
            if (view != null) {
                view.findViewById(R.id.progressBar).setVisibility(View.GONE);
            }
        }

        @Override
        public void onSuccess() throws Exception {
            View view = getView();
            if (view != null) {
                view.findViewById(R.id.progressBar).setVisibility(View.GONE);
            }
        }

        @Override
        public void onCancelled() throws Exception {
            View view = getView();
            if (view != null) {
                view.findViewById(R.id.progressBar).setVisibility(View.GONE);
            }
        }
    };

}
