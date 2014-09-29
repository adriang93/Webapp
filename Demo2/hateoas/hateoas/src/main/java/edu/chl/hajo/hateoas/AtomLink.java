package edu.chl.hajo.hateoas;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an Atom <link> element
 * Example
 *
 * <link rel="visibleNameOfLink" type="..."
 *     href="some URI"/>
 * 
 */
// Will create <link> element
@XmlRootElement(name = "link" )
public class AtomLink {

    @XmlAttribute(name = "rel")
    private String relationship;
    @XmlAttribute
    private String href;
    @XmlAttribute
    private String type;

    public AtomLink() {
    }

    public AtomLink(String rel, String href, String type) {
        this.relationship = rel;
        this.href = href;
        this.type = type;
    }

    public AtomLink(String rel, String href) {
        this(rel, href, "application/xml");
    }

    public String getRelationship() {
        return relationship;
    }

    public String getHref() {
        return href;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "AtomLink{" + "relationship=" + relationship + ", href=" + href + ", type=" + type + '}';
    }
}
