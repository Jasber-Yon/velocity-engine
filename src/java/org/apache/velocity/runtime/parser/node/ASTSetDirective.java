/* Generated By:JJTree: Do not edit this line. ASTSetDirective.java */

package org.apache.velocity.runtime.parser.node;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.Context;
import org.apache.velocity.runtime.Runtime;
import org.apache.velocity.runtime.exception.ReferenceException;
import org.apache.velocity.runtime.parser.*;

public class ASTSetDirective extends SimpleNode
{
    private Node right;
    private ASTReference left;
    private Object value;
    
    public ASTSetDirective(int id)
    {
        super(id);
    }

    public ASTSetDirective(Parser p, int id)
    {
        super(p, id);
    }


    /** Accept the visitor. **/
    public Object jjtAccept(ParserVisitor visitor, Object data)
    {
        return visitor.visit(this, data);
    }

    public boolean render(Context context, Writer writer)
        throws IOException
    {
        right = getRightHandSide();

        if (right.value(context) == null)
        {
            Runtime.error(new ReferenceException(
                "#set: " + right.literal() + " is not a valid reference."));
            
            return false;
        }                

        value = right.value(context);
        left = getLeftHandSide();
        
        if (left.jjtGetNumChildren() == 0)
            context.put(left.getFirstToken().image.substring(1), value);
        else
            left.setValue(context, value);
    
        return true;
    }

    private ASTReference getLeftHandSide()
    {
        return (ASTReference) jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
    }

    private Node getRightHandSide()
    {
        return jjtGetChild(0).jjtGetChild(0).jjtGetChild(1).jjtGetChild(0);
    }
}
