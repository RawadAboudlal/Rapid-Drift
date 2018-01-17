package com.rawad.rapiddrift.entity.component;

import com.rawad.rapiddrift.math.Vector3f;

/**
 * @author rabou017
 *
 */
public class ForwardComponent extends Component {
	
	private Vector3f forwardDirection = new Vector3f(1f, 0, 0);
	
	/**
	 * @return the forwardDirection
	 */
	public Vector3f getForwardDirection() {
		return forwardDirection;
	}
	
	/**
	 * @param forwardDirection the forwardDirection to set
	 */
	public void setForwardDirection(Vector3f forwardDirection) {
		this.forwardDirection = forwardDirection;
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.component.Component#clone()
	 */
	@Override
	public Component clone() {
		
		ForwardComponent forwardComp = new ForwardComponent();
		
		forwardComp.setForwardDirection(this.getForwardDirection().clone());
		
		return forwardComp;
		
	}

}
