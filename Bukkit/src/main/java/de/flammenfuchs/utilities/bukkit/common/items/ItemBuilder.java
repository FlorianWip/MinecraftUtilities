package de.flammenfuchs.utilities.bukkit.common.items;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import sun.jvm.hotspot.debugger.win32.coff.DebugVC50WrongNumericTypeException;

import java.util.Arrays;

@RequiredArgsConstructor
public class ItemBuilder {


    public static ItemBuilder material(Material material) {
        return new ItemBuilder(material);
    }

    private final Material material;

    private String displayName;
    private String[] lore;
    private MaterialData materialData;
    private int amount;
    private short durability;
    private EnchantmentEntity[] enchantments;
    private ItemFlag[] itemFlags;

    public ItemBuilder displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder lore(String... lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder materialData(MaterialData materialData) {
        this.materialData = materialData;
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder durability(short durability) {
        this.durability = durability;
        return this;
    }

    public ItemBuilder durability(int durability) {
        this.durability = (short) durability;
        return this;
    }

    public ItemBuilder enchantments(EnchantmentEntity... enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder itemFlags(ItemFlag... itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemStack.setAmount(amount);
        itemStack.setData(materialData);
        itemStack.setDurability(durability);

        if (enchantments != null) {
            if (enchantments.length > 0) {
                for (EnchantmentEntity enchantment : enchantments) {
                    itemStack.addEnchantment(enchantment.getEnchantment(), enchantment.getLevel());
                }
            }
        }

        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Arrays.asList(lore));
        itemMeta.addItemFlags(itemFlags);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
