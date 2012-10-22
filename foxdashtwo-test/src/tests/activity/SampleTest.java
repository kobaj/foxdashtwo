package tests.activity;

//Big thanks to https://github.com/jmschultz/Eclipse-Robolectric-Example

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import tests.runner.SampleTestRunner;
import android.graphics.Color;
import com.kobaj.math.android.RectF;

import com.kobaj.math.Functions;

@RunWith(SampleTestRunner.class)
public class SampleTest
{
	//TODO test every other functions method
	
	@Test
	public void testFunctionsSetEqualIntersects() throws Exception
	{
		RectF send_back = new RectF();
		RectF a = new RectF(0, 1, 1, 0);
		
		//general collisions
		
		//first that they are the exact same
		assertThat(Functions.setEqualIntersects(send_back, a, 0, 1, 1, 0), equalTo(true));
		assertThat(a.left, equalTo(send_back.left));
		assertThat(a.top, equalTo(send_back.top));
		assertThat(a.right, equalTo(send_back.right));
		assertThat(a.bottom, equalTo(send_back.bottom));
		
		//b is bigger
		assertThat(Functions.setEqualIntersects(send_back, a, -1, 2, 2, -1), equalTo(true));
		assertThat(send_back.left, equalTo(0.0f));
		assertThat(send_back.top, equalTo(1.0f));
		assertThat(send_back.right, equalTo(1.0f));
		assertThat(send_back.bottom, equalTo(0.0f));
		
		//b is smaller
		assertThat(Functions.setEqualIntersects(send_back, a, .25, .75, .75, .25), equalTo(true));
		assertThat(equalRectF(send_back, new RectF(.25f, .75f, .75f, .25f)), equalTo(true));
		
		//parallel collisions
		
		//b on top collision
		assertThat(Functions.setEqualIntersects(send_back, a, .25, 2.0, .75, .5), equalTo(true));
		assertThat(equalRectF(send_back, new RectF(.25f, 1.0f, .75f, .5f)), equalTo(true));
		
		//b on bottom collision
		assertThat(Functions.setEqualIntersects(send_back, a, .25, .5, .75, -.5), equalTo(true));
		assertThat(equalRectF(send_back, new RectF(.25f, .5f, .75f, 0)), equalTo(true));
		
		//b on left collision
		assertThat(Functions.setEqualIntersects(send_back, a, -1.0, .75, .5, .25), equalTo(true));
		assertThat(equalRectF(send_back, new RectF(0, .75f, .5f, .25f)), equalTo(true));
		
		//b on right collision
		assertThat(Functions.setEqualIntersects(send_back, a, .5, .75, 2.0, .25), equalTo(true));
		assertThat(equalRectF(send_back, new RectF(.5f, .75f, 1.0f, .25f)), equalTo(true));
		
		//corner collisions
		
		//b on top left collision
		assertThat(Functions.setEqualIntersects(send_back, a, -1.0, 2.0, .75, .5), equalTo(true));
		assertThat(equalRectF(send_back, new RectF(0, 1.0f, .75f, .5f)), equalTo(true));
		
		//b on bottom left collision
		assertThat(Functions.setEqualIntersects(send_back, a, -1.0, .5, .75, -.5), equalTo(true));
		assertThat(equalRectF(send_back, new RectF(0, .5f, .75f, 0)), equalTo(true));
		
		//b on top right collision
		assertThat(Functions.setEqualIntersects(send_back, a, .25, 2.0, 2.0, .5), equalTo(true));
		assertThat(equalRectF(send_back, new RectF(.25f, 1.0f, 1.0f, .5f)), equalTo(true));
		
		//b on bottom right collision
		assertThat(Functions.setEqualIntersects(send_back, a, .25, .5, 2.0, -.5), equalTo(true));
		assertThat(equalRectF(send_back, new RectF(.25f, .5f, 1.0f, 0)), equalTo(true));
		
		//no collisions small
		
		//b on top
		assertThat(Functions.setEqualIntersects(send_back, a, .25, 2.0, .75, 1.5), equalTo(false));
		assertThat(equalRectF(send_back, new RectF(0, 0, 0, 0)), equalTo(true));
		
		//b on bottom
		assertThat(Functions.setEqualIntersects(send_back, a, .25, -1.5, .75, -2.0), equalTo(false));
		assertThat(equalRectF(send_back, new RectF(0, 0, 0, 0)), equalTo(true));
		
		//b on left
		assertThat(Functions.setEqualIntersects(send_back, a, -2.0, .75, -1.0, .25), equalTo(false));
		assertThat(equalRectF(send_back, new RectF(0, 0, 0, 0)), equalTo(true));
		
		//b on right
		assertThat(Functions.setEqualIntersects(send_back, a, 1.5, .75, 2.0, .25), equalTo(false));
		assertThat(equalRectF(send_back, new RectF(0, 0, 0, 0)), equalTo(true));
		
		//no collisions big
		
		//b on top
		assertThat(Functions.setEqualIntersects(send_back, a, -1.0, 2.0, 2.0, 1.5), equalTo(false));
		assertThat(equalRectF(send_back, new RectF(0, 0, 0, 0)), equalTo(true));
		
		//b on bottom
		assertThat(Functions.setEqualIntersects(send_back, a, -1.0, -1.5, 2.0, -2.0), equalTo(false));
		assertThat(equalRectF(send_back, new RectF(0, 0, 0, 0)), equalTo(true));
		
		//b on left
		assertThat(Functions.setEqualIntersects(send_back, a, -2.0, 2.0, -1.0, -.25), equalTo(false));
		assertThat(equalRectF(send_back, new RectF(0, 0, 0, 0)), equalTo(true));
		
		//b on right
		assertThat(Functions.setEqualIntersects(send_back, a, 1.5, 2.0, 2.0, -.25), equalTo(false));
		assertThat(equalRectF(send_back, new RectF(0, 0, 0, 0)), equalTo(true));
		
	}
	
	@Test
	public void testEqualRectF() throws Exception
	{
		assertThat(equalRectF(new RectF(0, 1, 1, 0), new RectF(0, 2 ,1 , 0)), equalTo(false));
		assertThat(equalRectF(new RectF(0, 1, 1, 0), new RectF(0, 1 ,1 , 0)), equalTo(true));
	}
	
	private boolean equalRectF(RectF a, RectF b)
	{
		return (a.left == b.left &&
				a.top == b.top &&
				a.right == b.right &&
				a.bottom == b.bottom);
	}
	
	@Test
	public void testFunctionsRectangularToRadius() throws Exception
	{
		assertThat(Functions.rectangularToRadius(0,1), equalTo(1.0));
		assertThat(Functions.rectangularToRadius(1,1), equalTo(Math.sqrt(2)));
		assertThat(Functions.rectangularToRadius(1,0), equalTo(1.0));
		assertThat(Functions.rectangularToRadius(0,0), equalTo(0.0));
	}
	
	@Test
	public void testFunctionsRectangularToDegree() throws Exception
	{
		assertThat(Functions.rectangularToDegree(0,1), equalTo(90.0));
		assertThat(Functions.rectangularToDegree(1,1), equalTo(45.0));
		assertThat(Functions.rectangularToDegree(1,0), equalTo(0.0));
		assertThat(Functions.rectangularToDegree(0,0), equalTo(0.0));
	}
	
	@Test
	public void testFunctionsPolarToX() throws Exception
	{
		assertThat(Math.toRadians(0), equalTo(0.0));
		assertThat(Math.toRadians(180), equalTo(Math.PI));
		assertThat(Math.sin(Math.PI), not(0.0)); //because of this, we have to round below
		
		assertThat(Functions.polarToX(0, 1), equalTo(1.0));
		assertThat(Math.round(Functions.polarToX(90, 1)), equalTo((long) 0.0));
		assertThat(Math.round(Functions.polarToX(-90, 1)), equalTo((long) 0.0));
		assertThat(Math.round(Functions.polarToX(180, 1)), equalTo((long) -1.0)); //round here
	}
	
	@Test
	public void testFunctionsPolarToY() throws Exception
	{
		assertThat(Math.toRadians(0), equalTo(0.0));
		assertThat(Math.toRadians(180), equalTo(Math.PI));
		assertThat(Math.sin(Math.PI), not(0.0)); //because of this, we have to round below
		
		assertThat(Functions.polarToY(0, 1), equalTo(0.0));
		assertThat(Functions.polarToY(90, 1), equalTo(1.0));
		assertThat(Functions.polarToY(-90, 1), equalTo(-1.0));
		assertThat(Math.round(Functions.polarToY(180, 1)), equalTo((long)0)); //round here
	}
	
	@Test
	public void testFunctionsNearestPowerOf2() throws Exception
	{
		assertThat(Functions.nearestPowerOf2(10), equalTo(16));
		assertThat(Functions.nearestPowerOf2(1), equalTo(1));
		assertThat(Functions.nearestPowerOf2(0), equalTo(0));
		assertThat(Functions.nearestPowerOf2(-1), equalTo(0));
	}
	
	@Test
	public void testFunctionsSpeed() throws Exception
	{
		assertThat(com.kobaj.math.Functions.speed(2, -5), equalTo(5.3851648071345040312507104915403));
		assertThat(com.kobaj.math.Functions.speed(1, 1), equalTo(1.4142135623730950488016887242097));
		assertThat(com.kobaj.math.Functions.speed(0, 0), equalTo(0.0));
	}
	
	@Test
	public void testFunctionsRed() throws Exception
	{
		assertThat(com.kobaj.math.Functions.red(Color.BLUE), equalTo(0));
		assertThat(com.kobaj.math.Functions.red(Color.RED), equalTo(255));
		assertThat(com.kobaj.math.Functions.red(Color.GREEN), equalTo(0));
	}
	
	@Test
	public void testFunctionsGreen() throws Exception
	{
		assertThat(com.kobaj.math.Functions.green(Color.BLUE), equalTo(0));
		assertThat(com.kobaj.math.Functions.green(Color.RED), equalTo(0));
		assertThat(com.kobaj.math.Functions.green(Color.GREEN), equalTo(255));
	}
	
	@Test
	public void testFunctionsBlue() throws Exception
	{
		assertThat(com.kobaj.math.Functions.blue(Color.BLUE), equalTo(255));
		assertThat(com.kobaj.math.Functions.blue(Color.RED), equalTo(0));
		assertThat(com.kobaj.math.Functions.blue(Color.GREEN), equalTo(0));
	}
	
	@Test
	public void testFunctionsAlpha() throws Exception
	{
		assertThat(com.kobaj.math.Functions.blue(Color.TRANSPARENT), equalTo(0));
		assertThat(com.kobaj.math.Functions.alpha(Color.BLUE), equalTo(255));
		assertThat(com.kobaj.math.Functions.alpha(Color.RED), equalTo(255));
		assertThat(com.kobaj.math.Functions.alpha(Color.RED), equalTo(255));
	}
	
	@Test
	public void testFunctionsMakeColor() throws Exception
	{
		assertThat(com.kobaj.math.Functions.makeColor(255, 255, 255, 255), equalTo(Color.WHITE));
		assertThat(com.kobaj.math.Functions.makeColor(0, 0, 0, 255), equalTo(Color.BLACK));
	}
}
