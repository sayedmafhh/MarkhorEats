package com.grocery.markhoreats

import android.net.http.SslError
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import com.grocery.markhoreats.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var webview = binding.webview
        webview.webViewClient = SSLTolerentWebViewClient()
        webview.settings.builtInZoomControls = true
        webview.settings.javaScriptEnabled = true
        webview.settings.domStorageEnabled = true
        webview.loadUrl("https://www.markhoreats.com/")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class SSLTolerentWebViewClient : WebViewClient() {
        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            super.onReceivedSslError(view, handler, error)
            handler!!.proceed()
        }
    }
}