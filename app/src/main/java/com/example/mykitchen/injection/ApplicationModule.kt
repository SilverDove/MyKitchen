package com.example.mykitchen.injection

import com.example.mykitchen.MyKitchenViewModel
import org.koin.dsl.module

val presentationModule = module{
    //On choisit le type (single, factory etc...)
    //single : singleton i.e. garde en mémoire l'état
    factory{ MyKitchenViewModel() } //factory: à chaque fois que vous en demandé un, vous en crée un nouveau (create a new instance)

}