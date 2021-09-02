package de.flammenfuchs.utilities.bukkit.common.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.enchantments.Enchantment;

@AllArgsConstructor
@Getter
public class EnchantmentEntity {

    public static EnchantmentEntity of(Enchantment enchantment, int level) {return new EnchantmentEntity(enchantment, level);}

    private Enchantment enchantment;
    private int level;
}
