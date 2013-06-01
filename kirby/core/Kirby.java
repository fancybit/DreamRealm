/**
 * @author DreamLandModdingTeam
 *	Copyright (c) DreamLand Modding Team, 2013
 * 	版权许可：DreamLand 制作小组， 2013.
 */
package kirby.core;



import static kirby.core.lib.ModInfo.MOD_ID;
import static kirby.core.lib.ModInfo.MOD_NAME;
import static kirby.core.lib.ModInfo.MOD_VERSION;
import static kirby.core.lib.ModInfo.PACKET_CHANEL;

import java.io.File;

import kirby.blocks.InitBlocks;
import kirby.core.tab.TabDreamRealm;
import kirby.entities.InitEntities;
import kirby.items.InitItems;
import kirby.utils.ConfigManager;
import kirby.utils.Localization;
import kirby.utils.event.DRSoundManager;
import kirby.utils.event.EventHandler;
import kirby.utils.proxy.CommonProxy;
import kirby.worldgen.dream1.WorldProviderDream;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;


/**
 *mod主类
 */
@Mod(modid = MOD_ID, name = MOD_NAME, version = MOD_VERSION)//v0.0.2a
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
channels={PACKET_CHANEL})
public class Kirby {
	
		/**
		 * 加载代理
		 */
		@SidedProxy(clientSide = "kirby.utils.proxy.ClientProxy", 
								serverSide = "kirby.utils.proxy.CommonProxy")
		public static CommonProxy proxy;
	
		@Instance("mod_Kirby")
		public static Kirby instance;

		public static final String CONFIG_FILE = "kirby/main.conf";
		
		/**
		 * 美梦
		 */
		public static int Dimid = DimensionManager.getNextFreeDimId();
		/**
		 * 噩梦
		 */
		public static int Dimid2 = DimensionManager.getNextFreeDimId();
		
		/**
		 * Creative Tabs
		 */
		public static CreativeTabs customTab = new TabDreamRealm("customTab");
		
		/**
		 * @param event
		 * 预加载（设置、世界生成、注册Event）
		 */
		@PreInit
	    public void preInit(FMLPreInitializationEvent event)
	    {
			new ConfigManager(new File(event.getModConfigurationDirectory(), CONFIG_FILE));
			
			Localization.addLocalization("/kirby/lang/", "en_US");
			
			MinecraftForge.EVENT_BUS.register(new DRSoundManager());
			
			MinecraftForge.EVENT_BUS.register(new EventHandler());

			DimensionManager.registerProviderType(Dimid,
					WorldProviderDream.class, true);
			DimensionManager.registerDimension(Dimid, Dimid);

	    }
		
		/**
		 * @param event
		 * 加载（方块，物品，实体）
		 */
		@Init
	    public void init(FMLInitializationEvent event)
	    {
			new InitBlocks();
			
			new InitItems();
			
			new InitEntities();

			LanguageRegistry.instance().addStringLocalization
													("itemGroup.customTab", Localization.get("creativeTab.text"));
	    }
		
		/**
		 * @param Init
		 * 加载后
		 */
		@PostInit
		public void postInit(FMLPostInitializationEvent Init){
			
		}
		
		/* 
		 *  DONE: 版本0.0.1
		 *  
		 *  -第一个版本
		 *  
		 *  
	 	 *	DRTODO: 版本 0.0.2
	 	 *
	 	 * -区别梦与现实(在梦里死去就醒来)                                                                                                                                       好讽刺
	 	 * -入睡后才入梦
		 * -入睡后有一定几率不入梦
		 * -添加睡眠机器(带模型，可以百分百入梦)
		 * -DONE:添加外部化语言文件
		 * 
		 * 			DRXXX v0.0.2a
		 * 			-BUG DR-0001 //fixed              ←。←也就是还没fix
		 * 
		 * 
		 *
		 *  DRTODO:  版本0.0.3 (Pretty Scary Update)
		 * 
		 * -添加噩梦
		 * -添加梦魇(模型已经完成)
		 * -添加噩梦Boss(LS+1)
		 * -添加噩梦主题方块
		 * 
		 *  DRTODO: 版本0.0.4 (Wonderful Update)
		 *  
		 *  -添加美梦以及与美梦相关的方块和生物
		 *  
		 *  DRTODO: 版本0.0.5 (Story Update)
		 *  
		 *  -添加剧情(做一个有剧情的mod)
		 *  
		 *  DRTODO: 版本0.0.6 ()
		 *  
		 *  
		 */
}
