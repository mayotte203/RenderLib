package meldexun.renderlib.mixin.renderdistance;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import meldexun.renderlib.api.IMaxRenderDistanceAdjusting;
import meldexun.renderlib.config.RenderLibConfig;
import net.minecraft.tileentity.TileEntity;

@Mixin(TileEntity.class)
public class MixinTileEntity implements IMaxRenderDistanceAdjusting {

	@Unique
	private boolean initialized = false;
	@Unique
	private double multiplier = 1.0D;
	@Unique
	private double increment = 0.0D;

  public void initialize()
  {
      Double m = RenderLibConfig.tileEntityMaxRenderDistanceSquaredMultiplierListImpl.get((TileEntity) (Object) this);
      Double i = RenderLibConfig.tileEntityMaxRenderDistanceSquaredIncrementListImpl.get((TileEntity) (Object) this);
      if(m != null)
      {
        multiplier = m;
      }
      if(i != null)
      {
        increment = i;
      }
      
      initialized = true;
  }

	@Override
	public double getMultiplier() {
    if(!initialized)
    {
      initialize();
    }
		return multiplier;
	}

	@Override
	public double getIncrement() {
    if(!initialized)
    {
      initialize();
    }
		return increment;
	}

}
