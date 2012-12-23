package com.kobaj.level;

import org.simpleframework.xml.Element;

import android.graphics.Color;

import com.kobaj.foxdashtwo.R;
import com.kobaj.math.AverageMaker;
import com.kobaj.math.Constants;
import com.kobaj.math.Functions;
import com.kobaj.math.RectFExtended;
import com.kobaj.math.android.RectF;
import com.kobaj.opengldrawable.EnumDrawFrom;
import com.kobaj.opengldrawable.Quad.Quad;
import com.kobaj.opengldrawable.Quad.QuadColorShape;
import com.kobaj.opengldrawable.Quad.QuadCompressed;

public class LevelObject extends LevelEntityActive
{
	@Element
	public EnumLevelObject this_object;
	
	public final EnumDrawFrom draw_from = EnumDrawFrom.bottom_left;
	@Element
	public double x_pos; // screen coordinates
	@Element
	public double y_pos; // screen coordinates
	@Element
	public double z_plane; // generally 1-9, with 1 being closest to the 'front' or 'camera'
	@Element
	public double scale;
	@Element
	public double degree;
	@Element
	public String id = "unset"; // identifier.
	
	public Quad quad_object;
	
	// usually read only
	// screen coords
	public double width;
	public double height;
	
	// these dont change,
	public double x_pos_shader;
	public double y_pos_shader;
	
	public void onInitialize()
	{
		if (this_object == EnumLevelObject.bg1)
			quad_object = new QuadCompressed(R.raw.bg1, R.raw.bg1_alpha, 1659, 1064);
		else if (this_object == EnumLevelObject.bg2)
			quad_object = new QuadCompressed(R.raw.bg2, R.raw.bg2_alpha, 1551, 1080);
		else if (this_object == EnumLevelObject.floor1)
		{
			quad_object = new QuadCompressed(R.raw.floor1, R.raw.floor1_alpha, 465, 284);
			RectF previous = quad_object.phys_rect_list.get(0).main_rect;
			quad_object.phys_rect_list.add(new RectFExtended(previous.left, previous.top - Functions.screenHeightToShaderHeight(114), previous.right, previous.bottom));
			quad_object.phys_rect_list.remove(0);
		}
		else if (this_object == EnumLevelObject.floor2)
		{
			quad_object = new QuadCompressed(R.raw.floor2, R.raw.floor2_alpha, 309, 237);
			RectF previous = quad_object.phys_rect_list.get(0).main_rect;
			quad_object.phys_rect_list.add(new RectFExtended(previous.left, previous.top - Functions.screenHeightToShaderHeight(100), previous.right, previous.bottom));
			quad_object.phys_rect_list.remove(0);
		}
		else if (this_object == EnumLevelObject.floor3)
		{
			quad_object = new QuadCompressed(R.raw.floor3, R.raw.floor3_alpha, 443, 217);
			RectF previous = quad_object.phys_rect_list.get(0).main_rect;
			quad_object.phys_rect_list.add(new RectFExtended(previous.left, previous.top - Functions.screenHeightToShaderHeight(38), previous.right, previous.bottom));
			quad_object.phys_rect_list.remove(0);
		}
		else if (this_object == EnumLevelObject.fungus1)
			quad_object = new QuadCompressed(R.raw.fungus1, R.raw.fungus1_alpha, 73, 354);
		else if (this_object == EnumLevelObject.fungus2)
			quad_object = new QuadCompressed(R.raw.fungus2, R.raw.fungus2_alpha, 299, 278);
		else if (this_object == EnumLevelObject.fungus3)
			quad_object = new QuadCompressed(R.raw.fungus3, R.raw.fungus3_alpha, 57, 50);
		else if (this_object == EnumLevelObject.fungus4)
			quad_object = new QuadCompressed(R.raw.fungus4, R.raw.fungus4_alpha, 87, 78);
		else if (this_object == EnumLevelObject.layer14)
			quad_object = new QuadCompressed(R.raw.layer14, R.raw.layer14_alpha, 247, 226);
		else if (this_object == EnumLevelObject.layer18)
			quad_object = new QuadCompressed(R.raw.layer18, R.raw.layer18_alpha, 676, 280);
		else if (this_object == EnumLevelObject.layer17)
			quad_object = new QuadCompressed(R.raw.layer17, R.raw.layer17_alpha, 696, 347);
		else if (this_object == EnumLevelObject.shroom1)
			quad_object = new QuadCompressed(R.raw.shroom1, R.raw.shroom1_alpha, 256, 367);
		else if (this_object == EnumLevelObject.shroom2)
			quad_object = new QuadCompressed(R.raw.shroom2, R.raw.shroom2_alpha, 107, 111);
		else if (this_object == EnumLevelObject.shroom3)
			quad_object = new QuadCompressed(R.raw.shroom3, R.raw.shroom3_alpha, 111, 113);
		else if (this_object == EnumLevelObject.shroom4)
			quad_object = new QuadCompressed(R.raw.shroom4, R.raw.shroom4_alpha, 183, 229);
		else if (this_object == EnumLevelObject.transparent)
			quad_object = new QuadCompressed(R.raw.transparent_alpha, R.raw.transparent_alpha, (int) width, (int) height);
		else if (this_object == EnumLevelObject.test)
			quad_object = new QuadColorShape(0, 200, 200, 0, Color.WHITE, 0);
		else if (this_object == EnumLevelObject.floating1)
		{
			quad_object = new QuadCompressed(R.raw.floating1, R.raw.floating1_alpha, 391, 198);
			RectF previous = quad_object.phys_rect_list.get(0).main_rect;//
			quad_object.phys_rect_list.add(new RectFExtended(previous.left + Functions.screenWidthToShaderWidth(45), //
					previous.top - Functions.screenHeightToShaderHeight(30),//
					previous.right - Functions.screenWidthToShaderWidth(45),//
					previous.bottom + Functions.screenHeightToShaderHeight(50)));//
			quad_object.phys_rect_list.remove(0);
		}
		else
			quad_object = new QuadColorShape(0, 200, 200, 0, Color.RED, 0);
		
		quad_object.setZPos(quad_object.z_pos - (z_plane * Constants.z_modifier));
		quad_object.setXYPos(Functions.screenXToShaderX(x_pos), Functions.screenYToShaderY(y_pos), draw_from);
		
		// note how these are set AFTER
		this.x_pos_shader = quad_object.x_pos;
		this.y_pos_shader = quad_object.y_pos;
		
		width = quad_object.width;
		height = quad_object.height;
		
		if (degree != 0)
			quad_object.setRotationZ(degree);
		if (scale != 1)
			quad_object.setScale(scale);

	}
	
	AverageMaker y_average = new AverageMaker(10);
	
	public void onUpdate(double delta)
	{
		if (this_object == EnumLevelObject.floating1)
		{
			Constants.physics.addSpringY(Constants.floating_spring, Constants.floating_damper, 0, quad_object.y_pos - y_pos_shader, quad_object);
			Constants.physics.integratePhysics(delta, quad_object);
		}
	}
	
	public void onDrawObject()
	{
		if (active)
			quad_object.onDrawAmbient();
	}
}