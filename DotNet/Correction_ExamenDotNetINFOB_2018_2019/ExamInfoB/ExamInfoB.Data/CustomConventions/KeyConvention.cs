using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration.Conventions;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamInfoB.Data.CustomConventions
{
    public class KeyConvention:Convention
    {
        public KeyConvention()
        {
            Properties().Where(p => p.Name.StartsWith("Id")).Configure(p => p.IsKey());
        }
    }
}
