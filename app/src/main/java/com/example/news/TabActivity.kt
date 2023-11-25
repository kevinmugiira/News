    package com.example.news

    import android.graphics.Color
    import android.graphics.drawable.ColorDrawable
    import android.os.Bundle
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.ActionBar
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import androidx.viewpager.widget.ViewPager
    import com.google.android.material.tabs.TabLayout

    class TabActivity : AppCompatActivity() {

        private var tabLayout: TabLayout? = null
        private var viewPager: ViewPager? = null
        private var adapter: MyPageAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_tab)

            // Move view initialization outside the setOnApplyWindowInsetsListener block
            tabLayout = findViewById(R.id.tablayout)
            viewPager = findViewById(R.id.viewPager)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            // Initialize adapter and setup ViewPager
            adapter = MyPageAdapter(supportFragmentManager)
            viewPager?.adapter = adapter

            // Assign TabLayout with ViewPager
            tabLayout?.setupWithViewPager(viewPager)

            // Attach TabLayout to ViewPager listener
            viewPager?.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(tabLayout)
            )

            // Set ActionBar properties
            val bar: ActionBar? = supportActionBar
            bar?.title = "News list"
            bar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("blue")))
        }
    }