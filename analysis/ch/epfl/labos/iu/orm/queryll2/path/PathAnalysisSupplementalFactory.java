package ch.epfl.labos.iu.orm.queryll2.path;

import java.util.List;

import ch.epfl.labos.iu.orm.queryll2.symbolic.TypedValue;

public interface PathAnalysisSupplementalFactory<T, U>
{
   PathAnalysisMethodChecker createMethodChecker();
   U createMethodAnalysisResults();
   void addPath(U resultsHolder, 
         TypedValue returnValue, 
         List<TypedValue.ComparisonValue> conditions,
         PathAnalysisMethodChecker methodChecker);
}
