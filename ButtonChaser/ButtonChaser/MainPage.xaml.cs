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
using System.Windows.Threading;

namespace ButtonChaser
{
    public partial class MainPage : PhoneApplicationPage
    {
        Random random;
        int aWidth;
        int aHeight;
        int score;
        DispatcherTimer timer;
        // Constructor
        public MainPage()
        {
            InitializeComponent();
            
            
        }

        void timer_Tick(object sender, EventArgs e)
        {
            int x = random.Next(aWidth) - (int)button1.ActualWidth;
            int y = random.Next(aHeight) - (int)button1.ActualHeight;
            if (x < 10)
                x = 10;
            if (y < 10)
                y = 10;
            button1.Margin = new Thickness(x, y, 0, 0);
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            score += 1;
            ScoreTextBlock.Text = "Score: " + score;
        }

        private void ContentPanel_SizeChanged(object sender, SizeChangedEventArgs e)
        {
            aWidth = (int)e.NewSize.Width;
            aHeight = (int)e.NewSize.Height;
        }

        private void startButton_Click(object sender, RoutedEventArgs e)
        {

        }

        private void stopButton_Click(object sender, RoutedEventArgs e)
        {
            timer.Stop();

        }

        private void easyButton_Click(object sender, RoutedEventArgs e)
        {
            random = new Random();
            timer = new DispatcherTimer();
            timer.Start();
            timer.Interval = new TimeSpan(0, 0, 0, 0, 1000);
            timer.Tick += new EventHandler(timer_Tick);
        }

        private void mediumButton_Click(object sender, RoutedEventArgs e)
        {
            random = new Random();
            timer = new DispatcherTimer();
            timer.Start();
            timer.Interval = new TimeSpan(0, 0, 0, 0, 100);
            timer.Tick += new EventHandler(timer_Tick);
        }

        private void hardButton_Click(object sender, RoutedEventArgs e)
        {
            random = new Random();
            timer = new DispatcherTimer();
            timer.Start();
            timer.Interval = new TimeSpan(0, 0, 0, 0, 10);
            timer.Tick += new EventHandler(timer_Tick);
        }
    }
}