package com.rawad.rapiddrift.entity;


/**
 * @author Rawad
 *
 */
public class AttachmentComponent extends Component {
	
	private Entity attachedTo;
	
	/**
	 * @return the attachedTo
	 */
	public Entity getAttachedTo() {
		return attachedTo;
	}
	
	/**
	 * @param attachedTo the attachedTo to set
	 */
	public void setAttachedTo(Entity attachedTo) {
		this.attachedTo = attachedTo;
	}
	
	/**
	 * @see com.rawad.rapiddrift.entity.Component#clone()
	 */
	@Override
	public Component clone() {
		
		AttachmentComponent attachmentComp = new AttachmentComponent();
		
		attachmentComp.setAttachedTo(this.getAttachedTo());
		
		return attachmentComp;
		
	}
	
}
