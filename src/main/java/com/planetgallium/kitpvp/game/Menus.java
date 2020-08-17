package com.planetgallium.kitpvp.game;

import com.planetgallium.kitpvp.menu.KitMenu;
import com.planetgallium.kitpvp.menu.PreviewMenu;
import com.planetgallium.kitpvp.util.Resources;

public class Menus {

    private KitMenu kitMenu;
    private PreviewMenu previewMenu;

    public Menus(Resources resources) {
        this.kitMenu = new KitMenu(resources);
        this.previewMenu = new PreviewMenu();
    }

    public KitMenu getKitMenu() { return kitMenu; }

    public PreviewMenu getPreviewMenu() { return previewMenu; }

}