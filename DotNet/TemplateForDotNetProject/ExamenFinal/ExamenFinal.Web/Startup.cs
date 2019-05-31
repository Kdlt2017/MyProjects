using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(ExamenFinal.Web.Startup))]
namespace ExamenFinal.Web
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
