using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using System.Device.Location;

namespace LocationExample
{
    public partial class MainPage : PhoneApplicationPage
    {

        GeoCoordinateWatcher gcw;

        // Constructor
        public MainPage()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            gcw = new GeoCoordinateWatcher(GeoPositionAccuracy.High);
            gcw.MovementThreshold = 20;
            gcw.StatusChanged += new EventHandler<GeoPositionStatusChangedEventArgs>(gcw_StatusChanged);
            gcw.Start();
        }

        void gcw_StatusChanged(object sender, GeoPositionStatusChangedEventArgs e)
        {
            if (e.Status == GeoPositionStatus.Ready)
            {
                GeoCoordinate coord = gcw.Position.Location;
                latitude.Text = "Latitude :" + coord.Latitude.ToString("0.000");
                longitude.Text = "Longitude: " + coord.Longitude.ToString("0.000");
                // After you have the location, stop the service to conserve power
                gcw.Stop();
            }
            if (e.Status == GeoPositionStatus.Disabled || e.Status == GeoPositionStatus.NoData)
            {
                latitude.Text = "GPS Disabled";
                longitude.Text = "";
                gcw.Stop();
            }
        }
    }
}