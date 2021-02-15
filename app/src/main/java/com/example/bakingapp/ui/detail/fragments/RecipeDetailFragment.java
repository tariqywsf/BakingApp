package com.example.bakingapp.ui.detail.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.databinding.RecipeDetailFragmentBinding;
import com.example.bakingapp.model.Recipes;
import com.example.bakingapp.ui.detail.adapters.IngredientsAdapter;
import com.example.bakingapp.ui.detail.adapters.StepsAdapter;
import com.example.bakingapp.ui.detail.viewmodels.RecipeDetailViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RecipeDetailFragment extends Fragment {

    public static final String CURRENT_RECIPE = "current_recipe";
    private Recipes currentRecipe;
    private RecipeDetailFragmentBinding recipeDetailFragmentBinding;
    private StepsAdapter.OnStepClickListener onStepClickListener;

    public static RecipeDetailFragment newInstance() {
        return new RecipeDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.recipe_detail_fragment, container, false);

        recipeDetailFragmentBinding = DataBindingUtil
                .inflate(inflater,
                        R.layout.recipe_detail_fragment,
                        container,false);



        return recipeDetailFragmentBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecipeDetailViewModel mViewModel = new ViewModelProvider(requireActivity()).get(RecipeDetailViewModel.class);
        currentRecipe = mViewModel.getCurrentRecipe();

        setupIngredientsRecyclerView();

        setupStepsRecyclerView();
    }

    private void setupStepsRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        StepsAdapter stepsAdapter = new StepsAdapter(currentRecipe.getSteps(),onStepClickListener);
        stepsAdapter.setStepsData(currentRecipe.getSteps());

        RecyclerView stepsRecyclerView = recipeDetailFragmentBinding.stepsRecyclerview;
        stepsRecyclerView.setLayoutManager(layoutManager);
        stepsRecyclerView.setHasFixedSize(true);
        stepsRecyclerView.setAdapter(stepsAdapter);
    }

    private void setupIngredientsRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
        ingredientsAdapter.setIngredientsData(currentRecipe.getIngredients());

        RecyclerView ingredientsRecyclerView = recipeDetailFragmentBinding.ingredientsRecyclerview;
        ingredientsRecyclerView.setLayoutManager(layoutManager);
        ingredientsRecyclerView.setHasFixedSize(true);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

    }

}