precision mediump float;       	// Set the default precision to medium. We don't need as high of a precision in the fragment shader.

const float const_atten = 1.0;  // default value is 1
const float linear_atten = 0.2;  // default value is 0
const float quad_atten = 0.8; // default value is 0

uniform vec3 u_LightPos;       	// The position of the light in eye space.
uniform sampler2D u_Texture;    // The input texture.

varying vec3 v_Position;		// Interpolated position for this fragment.
varying vec4 v_Color;          	// This is the color from the vertex shader interpolated across the 
  								// triangle per fragment.
varying vec3 v_Normal;         	// Interpolated normal for this fragment.
varying vec2 v_TexCoordinate;   // Interpolated texture coordinate per fragment.
 
uniform vec3 u_LightDir;		//direction of the light
uniform float u_LightAngle;		//how big the angle of light is.
 
// The entry point for our fragment shader.
void main()                    		
{                              
	// Will be used for attenuation.
    float distance = length(u_LightPos - v_Position);                  
	
	// Get a lighting direction vector from the light to the vertex.
    vec3 lightVector = normalize(u_LightPos - v_Position);              	

	// Calculate the dot product of the light vector and vertex normal. If the normal and light vector are
	// pointing in the same direction then it will get max illumination.
    float diffuse = max(dot(v_Normal, lightVector), 0.0);               	  		  													  

	// Calculate if its in the spot light
	float spotFactor = max(dot(lightVector, -normalize(u_LightDir)), 0.0);
	if(spotFactor < cos(radians(u_LightAngle)))
		diffuse = 0.0;
		
	// Add attenuation. 
	float attenuation =  1.0 / (const_atten + linear_atten * distance + quad_atten * distance * distance);
    diffuse = diffuse * attenuation;
    
    // Add ambient lighting
    diffuse = diffuse + 0.0;  

	// Multiply the color by the diffuse illumination level and texture value to get final output color.
    gl_FragColor = (v_Color * diffuse * texture2D(u_Texture, v_TexCoordinate));                             		
}                                                                     	
