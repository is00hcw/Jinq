package ch.epfl.labos.iu.orm.queryll2.path;

import ch.epfl.labos.iu.orm.queryll2.symbolic.ConstantValue;
import ch.epfl.labos.iu.orm.queryll2.symbolic.TypedValue;
import ch.epfl.labos.iu.orm.queryll2.symbolic.TypedValueRewriterWalker;
import ch.epfl.labos.iu.orm.queryll2.symbolic.TypedValueVisitor;

/**
 * Java uses integers for booleans. This rewriter will rewrite integer
 * constants as boolean constants if the integers seem to be used as
 * booleans.
 * 
 * It cannot handle the case where variables are used simultaneously as
 * integers and booleans, but I don't think any compiler generates such code.
 * The parameter is true if the tree node should be treated as a boolean.
 *
 */
public class SymbExBooleanRewriter extends TypedValueRewriterWalker<Boolean, RuntimeException>
{

   public SymbExBooleanRewriter()
   {
      super(new TypedValueVisitor<Boolean, TypedValue, RuntimeException>() 
         {
            @Override
            public TypedValue defaultValue(TypedValue val, Boolean in) 
            {
               return val;
            }
         }, 
         new TypedValueVisitor<Boolean, Boolean, RuntimeException>() 
         {
            @Override
            public Boolean defaultValue(TypedValue val, Boolean in) 
            {
               return false;
            }
            @Override
            public Boolean notOpValue(TypedValue.NotValue val, Boolean in) 
            {
               return true;
            }
            @Override
            public Boolean binaryOpValue(TypedValue.BinaryOperationValue val, Boolean in) 
            {
               if ("AND".equals(val.operation) || "OR".equals(val.operation))
                  return true;
               return defaultValue(val, in);
            }
            
         });
   }
   
   public TypedValue integerConstantValue(ConstantValue.IntegerConstant val, Boolean in) 
   {
      if (in.booleanValue())
      {
         if (val.getConstant().intValue() == 0)
            return new ConstantValue.BooleanConstant(false);
         else
            return new ConstantValue.BooleanConstant(true);
      }
      return val;
   }
}
