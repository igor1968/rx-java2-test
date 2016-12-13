package com.igordanilchik.daggertest;


import com.igordanilchik.daggertest.ui.fragment.CategoriesFragment;
import com.igordanilchik.daggertest.ui.fragment.OfferFragment;
import com.igordanilchik.daggertest.ui.fragment.OffersFragment;

import dagger.Subcomponent;

@SuppressWarnings("WeakerAccess")
@Subcomponent(modules = RepositoryModule.class)
public interface RepositoryComponent {
    CategoriesFragment inject(CategoriesFragment categoriesFragment);
    OffersFragment inject(OffersFragment offersFragment);
    OfferFragment inject(OfferFragment offerFragment);
}
