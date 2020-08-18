/**@author Z. Mohamed Osama*/

package com.engine.world;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BodyProperty {

	private BodyType bdtype;
	private float restitution;
	private float density;
	private float friction;

	private short categoryBits;
	private short bitsMask;

	private boolean isSensor;

	public BodyProperty(BodyType bdtype, float restitution, float density, float friction, short categoryBits,
			short bitsMask, boolean isSensor) {

		this.setBdtype(bdtype);

		if (bdtype == BodyType.DynamicBody) {

			this.setRestitution(restitution);

		} else {

			this.setRestitution(0.0f);
		}

		this.setDensity(density);
		this.setFriction(friction);
		this.setCategoryBits(categoryBits);
		this.setBitsMask(bitsMask);
		this.setSensor(isSensor);
	}

	/**
	 * @return the bdtype
	 */
	public BodyType getBdtype() {
		return bdtype;
	}

	/**
	 * @param bdtype the bdtype to set
	 */
	public void setBdtype(BodyType bdtype) {
		this.bdtype = bdtype;
	}

	/**
	 * @return the restitution
	 */
	public float getRestitution() {
		return restitution;
	}

	/**
	 * @param restitution the restitution to set
	 */
	public void setRestitution(float restitution) {
		this.restitution = restitution;
	}

	/**
	 * @return the density
	 */
	public float getDensity() {
		return density;
	}

	/**
	 * @param density the density to set
	 */
	public void setDensity(float density) {
		this.density = density;
	}

	/**
	 * @return the friction
	 */
	public float getFriction() {
		return friction;
	}

	/**
	 * @param friction the friction to set
	 */
	public void setFriction(float friction) {
		this.friction = friction;
	}

	/**
	 * @return the categoryBits
	 */
	public short getCategoryBits() {
		return categoryBits;
	}

	/**
	 * @param categoryBits the categoryBits to set
	 */
	public void setCategoryBits(short categoryBits) {
		this.categoryBits = categoryBits;
	}

	/**
	 * @return the bitsMask
	 */
	public short getBitsMask() {
		return bitsMask;
	}

	/**
	 * @param bitsMask the bitsMask to set
	 */
	public void setBitsMask(short bitsMask) {
		this.bitsMask = bitsMask;
	}

	/**
	 * @return the isSensor
	 */
	public boolean isSensor() {
		return isSensor;
	}

	/**
	 * @param isSensor the isSensor to set
	 */
	public void setSensor(boolean isSensor) {
		this.isSensor = isSensor;
	}

}
